package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.UserFavoriteDao
import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef
import com.example.libraryapp.data.mapping.BookMapper
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.UserFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class UserFavoriteRepositoryImpl @Inject constructor(
    private val userFavoriteDao: UserFavoriteDao
) : UserFavoriteRepository {
    override suspend fun create(userId: Uuid, bookId: Uuid) {
        userFavoriteDao.insert(UserFavoriteCrossRef(userId, bookId))
    }

    override suspend fun delete(userId: Uuid, bookId: Uuid) {
        userFavoriteDao.delete(UserFavoriteCrossRef(userId, bookId))
    }

    override fun readByUserId(userId: Uuid): Flow<List<BookModel>> {
        return userFavoriteDao.selectByUserId(userId).map { entities ->
            entities.map { BookMapper.toDomain(it) }
        }
    }
}