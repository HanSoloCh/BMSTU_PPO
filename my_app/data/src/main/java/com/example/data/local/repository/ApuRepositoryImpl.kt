package com.example.data.local.repository

import com.example.data.local.entity.ApuEntity
import com.example.data.local.mapping.ApuMapper
import com.example.data.local.specification.ApuSpecToExpressionMapper
import com.example.domain.model.ApuModel
import com.example.domain.repository.ApuRepository
import com.example.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ApuRepositoryImpl(
    private val db: Database
) : ApuRepository {
    override suspend fun readById(apuId: UUID?): ApuModel? = withContext(Dispatchers.IO) {
        transaction(db) {
            ApuEntity.selectAll().where { ApuEntity.id eq apuId }.firstOrNull()?.let {
                ApuMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(apuModel: ApuModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            ApuEntity.insertAndGetId {
                ApuMapper.toInsertStatement(apuModel, it)
            }.value
        }
    }

    override suspend fun update(apuModel: ApuModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            ApuEntity.update({ ApuEntity.id eq apuModel.id }) {
                ApuMapper.toUpdateStatement(apuModel, it)
            }
        }
    }

    override suspend fun deleteById(apuId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            ApuEntity.deleteWhere { ApuEntity.id eq apuId }
        }
    }

    override suspend fun isContain(spec: Specification<ApuModel>) = withContext(Dispatchers.IO) {
        query(spec).isNotEmpty()
    }

    override suspend fun query(spec: Specification<ApuModel>): List<ApuModel> = withContext(Dispatchers.IO) {
        val expression = ApuSpecToExpressionMapper.map(spec)
        transaction(db) {
            ApuEntity.selectAll().where(expression).map { ApuMapper.toDomain(it) }
        }
    }
}