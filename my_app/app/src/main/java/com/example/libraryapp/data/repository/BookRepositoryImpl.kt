package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.AuthorEntity
import com.example.libraryapp.data.entity.BookAuthorCrossRef
import com.example.libraryapp.data.entity.BookEntity
import com.example.libraryapp.data.mapping.AuthorMapper
import com.example.libraryapp.data.mapping.BookMapper
import com.example.libraryapp.data.specification.BookSpecToExpressionMapper
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class BookRepositoryImpl(
    private val db: Database
) : BookRepository {
    override suspend fun readById(bookId: UUID): BookModel? = withContext(Dispatchers.IO) {
        transaction(db) {
            BookEntity.selectAll().where { BookEntity.id eq bookId }.firstOrNull()?.let {
                BookMapper.toDomain(it, getAuthorsByBookId(bookId))
            }
        }
    }

    override fun readByAuthorId(authorId: UUID): Flow<List<BookModel>> = flow {
        val result = transaction(db) {
            val books = (BookEntity innerJoin BookAuthorCrossRef)
                .select(BookEntity.columns)
                .where { BookAuthorCrossRef.authorId eq authorId }
                .toList()
            val authors = getAuthorsByBookId(books.map { it[BookEntity.id].value })
            books.map { BookMapper.toDomain(it, authors[it[BookEntity.id].value].orEmpty()) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)


    override suspend fun create(bookModel: BookModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            // Create book
            val bookId = BookEntity.insertAndGetId {
                BookMapper.toInsertStatement(bookModel, it)
            }.value
            // Create connections
            bookModel.authors.forEach { author ->
                BookAuthorCrossRef.insert {
                    it[BookAuthorCrossRef.bookId] = bookId
                    it[authorId] = author
                }
            }
            bookId
        }
    }

    override suspend fun update(bookModel: BookModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            val returnValue = BookEntity.update({ BookEntity.id eq bookModel.id }) {
                BookMapper.toUpdateStatement(bookModel, it)
            }
            if (returnValue > 0) {
                BookAuthorCrossRef.deleteWhere { bookId eq bookModel.id }

                bookModel.authors.forEach { author ->
                    BookAuthorCrossRef.insert {
                        it[bookId] = bookModel.id
                        it[authorId] = author
                    }
                }
            }
            returnValue
        }
    }

    override suspend fun deleteById(bookId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            BookEntity.deleteWhere { id eq bookId }
        }
    }

    override suspend fun isContain(spec: Specification<BookModel>) = withContext(Dispatchers.IO) {
        query(spec).first().isNotEmpty()
    }

    override fun query(spec: Specification<BookModel>): Flow<List<BookModel>> = flow {
        val expression = BookSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            val books = (BookEntity innerJoin BookAuthorCrossRef)
                .select(BookEntity.columns)
                .where { expression }
                .toList()
            val authors = getAuthorsByBookId(books.map { it[BookEntity.id].value })
            books.map { BookMapper.toDomain(it, authors[it[BookEntity.id].value].orEmpty()) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    private fun getAuthorsByBookId(bookId: UUID): List<AuthorModel> = transaction {
        getAuthorsByBookId(listOf(bookId))[bookId].orEmpty()
    }

    private fun getAuthorsByBookId(bookIds: List<UUID>): Map<UUID, List<AuthorModel>> = transaction {
        (AuthorEntity innerJoin BookAuthorCrossRef)
            .selectAll()
            .where { BookAuthorCrossRef.bookId inList bookIds }
            .map { row -> row[BookAuthorCrossRef.bookId].value to AuthorMapper.toDomain(row) }
            .groupBy({ it.first }, { it.second })
    }
}