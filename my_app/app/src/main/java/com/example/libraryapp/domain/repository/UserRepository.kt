package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.UserModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface UserRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun readById(userId: Uuid): UserModel?

    suspend fun create(userModel: UserModel)

    suspend fun update(userModel: UserModel)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun deleteById(userId: Uuid)

    suspend fun login(email: String, password: String): UserModel?
}