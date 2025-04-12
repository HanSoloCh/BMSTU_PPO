package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BookModel
import java.util.UUID

interface BookRepository {
    suspend fun readById(bookId: UUID): BookModel?

//    fun readByAuthorId(authorId: Uuid): Flow<List<BookModel>>

    suspend fun create(bookModel: BookModel): UUID

    suspend fun update(bookModel: BookModel): Int

    suspend fun deleteById(bookId: UUID): Int

//    fun query(specification: Specification<BookModel>): Flow<List<BookModel>>
}