package com.example.libraryapp.domain.repository

import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.UUID

interface UserFavoriteRepository {
    suspend fun create(userId: UUID, bookId: UUID): Pair<UUID, UUID>

    suspend fun delete(userId: UUID, bookId: UUID): Int
}