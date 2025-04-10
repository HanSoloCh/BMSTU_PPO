package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BookModel
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface UserFavoriteRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun create(userId: Uuid, bookId: Uuid)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun delete(userId: Uuid, bookId: Uuid)

    @OptIn(ExperimentalUuidApi::class)
    fun readByUserId(userId: Uuid): Flow<List<BookModel>>
}