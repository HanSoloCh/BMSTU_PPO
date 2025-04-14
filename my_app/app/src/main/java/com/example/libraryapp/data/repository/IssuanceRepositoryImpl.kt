package com.example.libraryapp.data.repository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import com.example.libraryapp.data.local.entity.IssuanceEntity
import com.example.libraryapp.data.mapping.IssuanceMapper
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.IssuanceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class IssuanceRepositoryImpl @Inject constructor() : IssuanceRepository {
    override suspend fun create(issuanceModel: IssuanceModel) = withContext(Dispatchers.IO) {
        transaction {
            IssuanceEntity.insert {
                IssuanceMapper.toInsertStatement(issuanceModel, it)
            }
                .resultedValues?.first()?.let { IssuanceMapper.toDomain(it).id }
                ?: throw NoSuchElementException("Error saving issuance: $issuanceModel")
            TODO()
        }
    }

    override suspend fun update(issuanceModel: IssuanceModel) = withContext(Dispatchers.IO) {
        transaction {
            IssuanceEntity.update({ IssuanceEntity.id eq issuanceModel.id }) {
                IssuanceMapper.toUpdateStatement(issuanceModel, it)
            }
        }
    }

    override suspend fun deleteById(issuanceId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            IssuanceEntity.deleteWhere { IssuanceEntity.id eq issuanceId }
        }
    }
}