package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.mapping.ApuMapper
import com.example.libraryapp.data.specification.ApuSpecToExpressionMapper
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.repository.ApuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class ApuRepositoryImpl @Inject constructor() : ApuRepository {
    override suspend fun readById(apuId: UUID): ApuModel? = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.selectAll().where { ApuEntity.id eq apuId }.firstOrNull()?.let {
                ApuMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(apuModel: ApuModel) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.insert {
                ApuMapper.toInsertStatement(apuModel, it)
            }
                .resultedValues?.first()?.let { ApuMapper.toDomain(it).id }
                ?: throw NoSuchElementException("Error saving apu: $apuModel")
        }
    }

    override suspend fun update(apuModel: ApuModel) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.update({ ApuEntity.id eq apuModel.id }) {
                ApuMapper.toUpdateStatement(apuModel, it)
            }
        }
    }

    override suspend fun deleteById(apuId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.deleteWhere { ApuEntity.id eq apuId }
        }
    }

    override fun query(spec: Specification<ApuModel>): Flow<List<ApuModel>> = flow {
        val expression = ApuSpecToExpressionMapper.map(spec)

        val result = transaction {
            ApuEntity.selectAll().where { expression }.map { ApuMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}