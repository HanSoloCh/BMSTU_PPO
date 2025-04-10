package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.UserDao
import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef
import com.example.libraryapp.data.mapping.UserMapper
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun readById(userId: Uuid): UserModel? {
        return userDao.selectById(userId)?.let {
            UserMapper.toDomain(it)
        }
    }

    override suspend fun create(userModel: UserModel) {
        userDao.insert(UserMapper.toData(userModel))
    }

    override suspend fun update(userModel: UserModel) {
        userDao.update(UserMapper.toData(userModel))
    }

    override suspend fun deleteById(userId: Uuid) {
        userDao.deleteById(userId)
    }

    override suspend fun login(email: String, password: String): UserModel? {
        return userDao.selectByEmailPassword(email, password)?.let {
            UserMapper.toDomain(it)
        }
    }
}