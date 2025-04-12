package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BookModel
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface UserFavoriteRepository {
    suspend fun create(userId: Uuid, bookId: Uuid)

    suspend fun delete(userId: Uuid, bookId: Uuid)

    fun readByUserId(userId: Uuid): Flow<List<BookModel>>
}