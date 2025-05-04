package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface UserRepository {
    suspend fun readById(userId: UUID): UserModel?

    suspend fun create(userModel: UserModel): UUID

    suspend fun update(userModel: UserModel): Int

    suspend fun deleteById(userId: UUID): Int

    suspend fun isContain(spec: Specification<UserModel>): Boolean

    fun query(spec: Specification<UserModel>): Flow<List<UserModel>>

    suspend fun login(email: String, password: String): UserModel?
}