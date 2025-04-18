package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.UserModel
import java.util.UUID

interface UserRepository {
    suspend fun readById(userId: UUID): UserModel?

    suspend fun create(userModel: UserModel): UUID

    suspend fun update(userModel: UserModel): Int

    suspend fun deleteById(userId: UUID): Int

    suspend fun isContain(userId: UUID): Boolean

    suspend fun login(email: String, password: String): UserModel?
}