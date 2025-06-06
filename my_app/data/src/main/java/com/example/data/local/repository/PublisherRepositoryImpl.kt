package com.example.data.local.repository

import com.example.data.local.entity.ApuEntity
import com.example.data.local.entity.PublisherEntity
import com.example.data.local.mapping.PublisherMapper
import com.example.data.local.specification.PublisherSpecToExpressionMapper
import com.example.domain.model.PublisherModel
import com.example.domain.repository.PublisherRepository
import com.example.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class PublisherRepositoryImpl(
    private val db: Database
) : PublisherRepository {
    override suspend fun readById(publisherId: UUID): PublisherModel? =
        withContext(Dispatchers.IO) {
            transaction(db) {
                PublisherEntity.selectAll().where { PublisherEntity.id eq publisherId }
                    .firstOrNull()?.let {
                        PublisherMapper.toDomain(it)
                    }
            }
        }

    override suspend fun create(publisherModel: PublisherModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            PublisherEntity.insertAndGetId {
                PublisherMapper.toInsertStatement(publisherModel, it)
            }.value
        }
    }

    override suspend fun update(publisherModel: PublisherModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            PublisherEntity.update({ PublisherEntity.id eq publisherModel.id }) {
                PublisherMapper.toUpdateStatement(publisherModel, it)
            }
        }
    }

    override suspend fun deleteById(publisherId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            ApuEntity.deleteWhere { ApuEntity.id eq publisherId }
        }
    }

    override suspend fun isContain(spec: Specification<PublisherModel>) =
        withContext(Dispatchers.IO) {
            query(spec).isNotEmpty()
        }

    override suspend fun query(spec: Specification<PublisherModel>): List<PublisherModel> =
        withContext(Dispatchers.IO) {
            val expression = PublisherSpecToExpressionMapper.map(spec)

            transaction(db) {
                PublisherEntity.selectAll().where { expression }.map { PublisherMapper.toDomain(it) }
            }
        }
}