package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.IssuanceEntity
import com.example.libraryapp.data.mapping.IssuanceMapper
import com.example.libraryapp.data.specification.IssuanceSpecToExpressionMapper
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.IssuanceRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class IssuanceRepositoryImpl @Inject constructor(
    private val db: Database
) : IssuanceRepository {
    override suspend fun create(issuanceModel: IssuanceModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            IssuanceEntity.insertAndGetId {
                IssuanceMapper.toInsertStatement(issuanceModel, it)
            }.value
            TODO("Сделать вызов функции по уменьшению общего числа книг")
        }
    }

    override suspend fun update(issuanceModel: IssuanceModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            IssuanceEntity.update({ IssuanceEntity.id eq issuanceModel.id }) {
                IssuanceMapper.toUpdateStatement(issuanceModel, it)
            }
        }
    }

    override suspend fun deleteById(issuanceId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            IssuanceEntity.deleteWhere { id eq issuanceId }
        }
    }

    override suspend fun isContain(spec: Specification<IssuanceModel>) =
        withContext(Dispatchers.IO) {
            query(spec).first().isNotEmpty()
        }

    override fun query(spec: Specification<IssuanceModel>): Flow<List<IssuanceModel>> = flow {
        val expression = IssuanceSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            IssuanceEntity.selectAll().where { expression }.map { IssuanceMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}