package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.IssuanceEntity
import com.example.libraryapp.data.entity.UserEntity
import com.example.libraryapp.data.mapping.IssuanceMapper
import com.example.libraryapp.data.mapping.UserMapper
import com.example.libraryapp.data.specification.IssuanceSpecToExpressionMapper
import com.example.libraryapp.data.specification.UserSpecToExpressionMapper
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val db: Database
) : UserRepository {
    override suspend fun readById(userId: UUID): UserModel? = withContext(Dispatchers.IO) {
        transaction(db) {
            UserEntity.selectAll().where { UserEntity.id eq userId }.firstOrNull()?.let {
                UserMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(userModel: UserModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            UserEntity.insertAndGetId {
                UserMapper.toInsertStatement(userModel, it)
            }.value
        }
    }

    override suspend fun update(userModel: UserModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            UserEntity.update({ UserEntity.id eq userModel.id }) {
                UserMapper.toUpdateStatement(userModel, it)
            }
        }
    }

    override suspend fun deleteById(userId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            UserEntity.deleteWhere { id eq userId }
        }
    }

    override suspend fun isContain(spec: Specification<UserModel>) = withContext(Dispatchers.IO) {
        query(spec).first().isNotEmpty()
    }

    override fun query(spec: Specification<UserModel>): Flow<List<UserModel>> = flow {
        val expression = UserSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            UserEntity.selectAll().where { expression }.map { UserMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        transaction(db) {
            UserEntity
                .selectAll()
                .where {
                    (UserEntity.email eq email) and (UserEntity.password eq password)
                }
                .firstOrNull()?.let { UserMapper.toDomain(it) }
        }
    }
}