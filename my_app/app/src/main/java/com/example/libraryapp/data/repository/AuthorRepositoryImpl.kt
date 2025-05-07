package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.AuthorEntity
import com.example.libraryapp.data.mapping.AuthorMapper
import com.example.libraryapp.data.specification.AuthorSpecToExpressionMapper
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import javax.inject.Inject


class AuthorRepositoryImpl @Inject constructor(
    private val db: Database
) : AuthorRepository {
    override suspend fun readById(authorId: UUID): AuthorModel? = withContext(Dispatchers.IO) {
        transaction(db) {
            AuthorEntity.selectAll().where { AuthorEntity.id eq authorId }.firstOrNull()?.let {
                AuthorMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(authorModel: AuthorModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            AuthorEntity.insertAndGetId {
                AuthorMapper.toInsertStatement(authorModel, it)
            }.value
        }
    }

    override suspend fun update(authorModel: AuthorModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            AuthorEntity.update({ AuthorEntity.id eq authorModel.id }) {
                AuthorMapper.toUpdateStatement(authorModel, it)
            }
        }
    }

    override suspend fun deleteById(authorId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            AuthorEntity.deleteWhere { id eq authorId }
        }
    }

    override suspend fun isContain(spec: Specification<AuthorModel>) = withContext(Dispatchers.IO) {
        query(spec).first().isNotEmpty()
    }

    override fun query(spec: Specification<AuthorModel>): Flow<List<AuthorModel>> = flow {
        val expression = AuthorSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            AuthorEntity.selectAll().where { expression }.map { AuthorMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    // TODO(Сделать получение всех книг автора)
}