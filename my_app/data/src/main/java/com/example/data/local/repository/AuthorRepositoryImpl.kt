package com.example.data.local.repository

import com.example.data.local.entity.AuthorEntity
import com.example.data.local.mapping.AuthorMapper
import com.example.data.local.specification.AuthorSpecToExpressionMapper
import com.example.domain.repository.AuthorRepository
import com.example.domain.specification.Specification
import com.example.domain.model.AuthorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*


class AuthorRepositoryImpl(
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
        query(spec).isNotEmpty()
    }

    override suspend fun query(spec: Specification<AuthorModel>): List<AuthorModel> = withContext(Dispatchers.IO) {
        val expression = AuthorSpecToExpressionMapper.map(spec)

        transaction(db) {
            AuthorEntity.selectAll().where { expression }.map { AuthorMapper.toDomain(it) }
        }
    }
}