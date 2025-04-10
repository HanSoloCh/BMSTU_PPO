package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.AuthorModel
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface AuthorRepository {
    suspend fun readById(authorId: Uuid): AuthorModel?

    fun readByBookId(bookId: Uuid): Flow<List<AuthorModel>>

    suspend fun create(authorModel: AuthorModel)

    suspend fun update(authorModel: AuthorModel)

    suspend fun deleteById(authorId: Uuid)

    suspend fun addBookToAuthor(authorId: Uuid, bookId: Uuid)
}