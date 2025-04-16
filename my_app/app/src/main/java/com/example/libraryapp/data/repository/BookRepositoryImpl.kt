package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.local.entity.BookAuthorCrossRef
import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.data.mapping.AuthorMapper
import com.example.libraryapp.data.mapping.BookMapper
import com.example.libraryapp.data.specification.BookSpecToExpressionMapper
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.Specification
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {
    override suspend fun readById(bookId: UUID): BookModel? = withContext(Dispatchers.IO) {
        transaction {
            val authors = (AuthorEntity innerJoin BookAuthorCrossRef)
                .selectAll().where { BookAuthorCrossRef.bookId eq bookId }
                .map { AuthorMapper.toDomain(it) }

            BookEntity.selectAll().where { BookEntity.id eq bookId }.firstOrNull()?.let {
                BookMapper.toDomain(it, authors)
            }
        }
    }

    override suspend fun create(bookModel: BookModel) = withContext(Dispatchers.IO) {
        transaction {
            // Create book
            val bookId = BookEntity.insertAndGetId {
                BookMapper.toInsertStatement(bookModel, it)
            }.value
            // Create connections
            bookModel.authors.forEach { author ->
                BookAuthorCrossRef.insert {
                    it[BookAuthorCrossRef.bookId] = bookId
                    it[BookAuthorCrossRef.authorId] = author
                }
            }
            bookId
        }
    }

    override suspend fun update(bookModel: BookModel) = withContext(Dispatchers.IO) {
        transaction {
            val returnValue = BookEntity.update({ BookEntity.id eq bookModel.id }) {
                BookMapper.toUpdateStatement(bookModel, it)
            }
            if (returnValue > 0) {
                BookAuthorCrossRef.deleteWhere { BookAuthorCrossRef.bookId eq bookModel.id }

                bookModel.authors.forEach { author ->
                    BookAuthorCrossRef.insert {
                        it[BookAuthorCrossRef.bookId] = bookModel.id
                        it[BookAuthorCrossRef.authorId] = author
                    }
                }
            }
            returnValue
        }
    }

    override suspend fun deleteById(bookId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            BookEntity.deleteWhere { BookEntity.id eq bookId }
        }
    }

    override fun query(spec: Specification<BookModel>): Flow<List<BookModel>> = flow {
        val expression = BookSpecToExpressionMapper.map(spec)

        val result = transaction {
            BookEntity.selectAll().where { expression }.map { BookMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}