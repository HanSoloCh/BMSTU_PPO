package com.example.data.local.repository

import com.example.data.local.entity.LanguageEntity
import com.example.data.local.mapping.LanguageMapper
import com.example.data.local.specification.LanguageSpecToExpressionMapper
import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import com.example.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class LanguageRepositoryImpl(
    private val db: Database
) : LanguageRepository {
    override suspend fun readById(bbkId: UUID): LanguageModel? = withContext(Dispatchers.IO) {
        transaction(db) {
            LanguageEntity.selectAll().where { LanguageEntity.id eq bbkId }.firstOrNull()?.let {
                LanguageMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(bbkModel: LanguageModel): UUID = withContext(Dispatchers.IO) {
        transaction(db) {
            LanguageEntity.insertAndGetId {
                LanguageMapper.toInsertStatement(bbkModel, it)
            }.value
        }
    }

    override suspend fun update(bbkModel: LanguageModel): Int = withContext(Dispatchers.IO) {
        transaction(db) {
            LanguageEntity.update({ LanguageEntity.id eq bbkModel.id }) {
                LanguageMapper.toUpdateStatement(bbkModel, it)
            }
        }
    }

    override suspend fun deleteById(bbkId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            LanguageEntity.deleteWhere { id eq bbkId }
        }
    }

    override suspend fun isContain(spec: Specification<LanguageModel>) = withContext(Dispatchers.IO) {
        query(spec).isNotEmpty()
    }

    override suspend fun query(spec: Specification<LanguageModel>): List<LanguageModel> = withContext(Dispatchers.IO) {
        val expression = LanguageSpecToExpressionMapper.map(spec)

        transaction(db) {
            LanguageEntity.selectAll().where { expression }.map { LanguageMapper.toDomain(it) }
        }
    }
}