package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.data.mapping.BbkMapper
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class BbkRepositoryImpl @Inject constructor() : BbkRepository {
    override suspend fun readById(bbkId: UUID): BbkModel? = withContext(Dispatchers.IO) {
        transaction {
            BbkEntity.selectAll().where { BbkEntity.id eq bbkId }.firstOrNull()?.let {
                BbkMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(bbkModel: BbkModel): UUID = withContext(Dispatchers.IO) {
        transaction {
            BbkEntity.insertAndGetId {
                BbkMapper.toInsertStatement(bbkModel, it)
            }.value
        }
    }

    override suspend fun update(bbkModel: BbkModel): Int = withContext(Dispatchers.IO) {
        transaction {
            BbkEntity.update({ BbkEntity.id eq bbkModel.id }) {
                BbkMapper.toUpdateStatement(bbkModel, it)
            }
        }
    }

    override suspend fun deleteById(bbkId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            BbkEntity.deleteWhere { BbkEntity.id eq bbkId }
        }
    }
}