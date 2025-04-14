package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.repository.UserFavoriteRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import javax.inject.Inject


class UserFavoriteRepositoryImpl @Inject constructor() : UserFavoriteRepository {
    override suspend fun create(userId: UUID, bookId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            UserFavoriteCrossRef.insert {
                it[UserFavoriteCrossRef.userId] = userId
                it[UserFavoriteCrossRef.bookId] = bookId
            }
        }
        userId to bookId
    }

    override suspend fun delete(userId: UUID, bookId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            UserFavoriteCrossRef
                .deleteWhere {
                    (UserFavoriteCrossRef.userId eq userId) and (UserFavoriteCrossRef.bookId eq bookId)
                }
        }
    }

    override fun query(specification: Specification<ApuModel>): Flow<List<ApuModel>> {
        TODO("Not yet implemented")
    }

}