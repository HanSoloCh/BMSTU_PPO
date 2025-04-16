package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.UserEntity
import com.example.libraryapp.data.mapping.UserMapper
import com.example.libraryapp.domain.model.UserModel
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import com.example.libraryapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun readById(userId: UUID): UserModel? = withContext(Dispatchers.IO) {
        transaction {
            UserEntity.selectAll().where { UserEntity.id eq userId }.firstOrNull()?.let {
                UserMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(userModel: UserModel) = withContext(Dispatchers.IO) {
        transaction {
            UserEntity.insertAndGetId {
                UserMapper.toInsertStatement(userModel, it)
            }.value
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
            UserEntity.deleteWhere { UserEntity.id eq userId }
        }
    }

    override suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        transaction {
            UserEntity
                .selectAll()
                .where {
                    (UserEntity.email eq email) and (UserEntity.password eq password)
                }
                .firstOrNull()?.let { UserMapper.toDomain(it) }
        }
    }
}