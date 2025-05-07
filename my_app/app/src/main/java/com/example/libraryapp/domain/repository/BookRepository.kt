package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.*

interface BookRepository {
    suspend fun readById(bookId: UUID): BookModel?

    fun readByAuthorId(authorId: UUID): Flow<List<BookModel>>

    suspend fun create(bookModel: BookModel): UUID

    suspend fun update(bookModel: BookModel): Int

    suspend fun deleteById(bookId: UUID): Int

    suspend fun isContain(spec: Specification<BookModel>): Boolean

    fun query(spec: Specification<BookModel>): Flow<List<BookModel>>
}