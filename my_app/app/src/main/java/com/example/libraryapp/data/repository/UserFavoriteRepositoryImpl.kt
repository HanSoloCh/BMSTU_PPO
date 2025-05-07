package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.UserFavoriteCrossRef
import com.example.libraryapp.domain.repository.UserFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import javax.inject.Inject


class UserFavoriteRepositoryImpl @Inject constructor(
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

    override fun readByUserId(userId: UUID): Flow<List<UUID>> = flow {
        emit(
            transaction(db) {
                UserFavoriteCrossRef
                    .selectAll()
                    .where { UserFavoriteCrossRef.userId eq userId }
                    .map { it[UserFavoriteCrossRef.bookId].value }
            }
        )
    }.flowOn(Dispatchers.IO)
}