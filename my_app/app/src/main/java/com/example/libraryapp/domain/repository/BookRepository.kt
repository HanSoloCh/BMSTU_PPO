package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.query.book.BookSpecification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface BookRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun readById(bookId: Uuid): BookModel?

    @OptIn(ExperimentalUuidApi::class)
    fun readByAuthorId(authorId: Uuid): Flow<List<BookModel>>

    suspend fun create(bookModel: BookModel): Int

    suspend fun update(bookModel: BookModel)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun deleteById(bookId: Uuid)

    fun query(specification: BookSpecification): Flow<List<BookModel>>
}