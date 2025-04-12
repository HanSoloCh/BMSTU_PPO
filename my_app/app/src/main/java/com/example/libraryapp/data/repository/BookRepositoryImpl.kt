package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.data.mapping.BookMapper
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {
    override suspend fun readById(bookId: UUID): BookModel? = withContext(Dispatchers.IO) {
        transaction {
                TODO()
        }
    }

    override suspend fun create(bookModel: BookModel) = withContext(Dispatchers.IO) {
        transaction {
            BookEntity.insert {
                BookMapper.toInsertStatement(bookModel, it)
            }
                .resultedValues?.first()?.let { BookMapper.toDomain(it).id }
                ?: throw NoSuchElementException("Error saving book: $bookModel")
        }
    }

    override suspend fun update(bookModel: BookModel) = withContext(Dispatchers.IO) {
        transaction {
            BookEntity.update({ BookEntity.id eq bookModel.id }) {
                BookMapper.toUpdateStatement(bookModel, it)
            }
        }
    }

    override suspend fun deleteById(bookId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.deleteWhere { BookEntity.id eq bookId }
        }
    }
}