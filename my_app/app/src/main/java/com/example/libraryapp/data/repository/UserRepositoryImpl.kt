package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.UserEntity
import com.example.libraryapp.data.mapping.UserMapper
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun readById(userId: UUID): UserModel? = withContext(Dispatchers.IO) {
        transaction {
            UserEntity.select { UserEntity.id eq userId }.firstOrNull()?.let {
                UserMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(userModel: UserModel) = withContext(Dispatchers.IO) {
        transaction {
            UserEntity.insert {
                UserMapper.toInsertStatement(userModel, it)
            }
                .resultedValues?.first()?.let { UserMapper.toDomain(it).id }
                ?: throw NoSuchElementException("Error saving user: $userModel")
        }
    }

    override suspend fun update(userModel: UserModel) = withContext(Dispatchers.IO) {
        transaction {
            UserEntity.update({ UserEntity.id eq userModel.id }) {
                UserMapper.toUpdateStatement(userModel, it)
            }
        }
    }

    override suspend fun deleteById(userId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.deleteWhere { UserEntity.id eq userId }
        }
    }
}