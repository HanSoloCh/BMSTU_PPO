package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.BbkEntity
import com.example.libraryapp.data.mapping.BbkMapper
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import kotlinx.coroutines.Dispatchers
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

class BbkRepositoryImpl @Inject constructor(
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

    override suspend fun isContain(bbkId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            BbkEntity.selectAll().where { BbkEntity.id eq bbkId }.empty().not()
        }
    }
}