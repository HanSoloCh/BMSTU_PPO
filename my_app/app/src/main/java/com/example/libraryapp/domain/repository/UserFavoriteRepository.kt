package com.example.libraryapp.domain.repository

import kotlinx.coroutines.flow.Flow
import java.util.*

interface UserFavoriteRepository {
    suspend fun create(userId: UUID, bookId: UUID): Pair<UUID, UUID>

    suspend fun delete(userId: UUID, bookId: UUID): Int

    fun readByUserId(userId: UUID): Flow<List<UUID>>
}