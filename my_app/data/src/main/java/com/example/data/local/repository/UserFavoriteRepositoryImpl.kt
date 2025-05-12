package com.example.data.local.repository

import com.example.data.local.entity.UserFavoriteCrossRef
import com.example.domain.repository.UserFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*


class UserFavoriteRepositoryImpl(
    private val db: Database
) : UserFavoriteRepository {
    override suspend fun create(userId: UUID, bookId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            UserFavoriteCrossRef.insert {
                it[UserFavoriteCrossRef.userId] = userId
                it[UserFavoriteCrossRef.bookId] = bookId
            }
        }
        userId to bookId
    }

    override suspend fun delete(userId: UUID, bookId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            UserFavoriteCrossRef
                .deleteWhere {
                    (UserFavoriteCrossRef.userId eq userId) and (UserFavoriteCrossRef.bookId eq bookId)
                }
        }
    }

    override suspend fun readByUserId(userId: UUID): List<UUID> = withContext(Dispatchers.IO) {
        transaction(db) {
            UserFavoriteCrossRef
                .selectAll()
                .where { UserFavoriteCrossRef.userId eq userId }
                .map { it[UserFavoriteCrossRef.bookId].value }
        }
    }
}