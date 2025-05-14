package com.example.domain.repository

import java.util.*

interface UserFavoriteRepository {
    suspend fun create(userId: UUID, bookId: UUID): Pair<UUID, UUID>

    suspend fun delete(userId: UUID, bookId: UUID): Int

    suspend fun readByUserId(userId: UUID): List<UUID>
}