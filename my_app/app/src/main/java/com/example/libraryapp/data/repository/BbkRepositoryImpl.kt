package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.BbkEntity
import com.example.libraryapp.data.mapping.BbkMapper
import com.example.libraryapp.data.specification.BbkSpecToExpressionMapper
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
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

class BbkRepositoryImpl(
    private val db: Database
) : BbkRepository {
    override suspend fun readById(bbkId: UUID): BbkModel? = withContext(Dispatchers.IO) {
        transaction(db) {
            BbkEntity.selectAll().where { BbkEntity.id eq bbkId }.firstOrNull()?.let {
                BbkMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(bbkModel: BbkModel): UUID = withContext(Dispatchers.IO) {
        transaction(db) {
            BbkEntity.insertAndGetId {
                BbkMapper.toInsertStatement(bbkModel, it)
            }.value
        }
    }

    override suspend fun update(bbkModel: BbkModel): Int = withContext(Dispatchers.IO) {
        transaction(db) {
            BbkEntity.update({ BbkEntity.id eq bbkModel.id }) {
                BbkMapper.toUpdateStatement(bbkModel, it)
            }
        }
    }

    override suspend fun deleteById(bbkId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            BbkEntity.deleteWhere { id eq bbkId }
        }
    }

    override suspend fun isContain(spec: Specification<BbkModel>) = withContext(Dispatchers.IO) {
        query(spec).first().isNotEmpty()
    }

    override fun query(spec: Specification<BbkModel>): Flow<List<BbkModel>> = flow {
        val expression = BbkSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            BbkEntity.selectAll().where { expression }.map { BbkMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}