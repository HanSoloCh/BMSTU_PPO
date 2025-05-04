package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.ApuEntity
import com.example.libraryapp.data.entity.IssuanceEntity
import com.example.libraryapp.data.entity.PublisherEntity
import com.example.libraryapp.data.mapping.IssuanceMapper
import com.example.libraryapp.data.mapping.PublisherMapper
import com.example.libraryapp.data.specification.IssuanceSpecToExpressionMapper
import com.example.libraryapp.data.specification.PublisherSpecToExpressionMapper
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
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

class PublisherRepositoryImpl @Inject constructor(
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
            ApuEntity.deleteWhere { id eq publisherId }
        }
    }

    override suspend fun isContain(spec: Specification<PublisherModel>) = withContext(Dispatchers.IO) {
        query(spec).first().isNotEmpty()
    }

    override fun query(spec: Specification<PublisherModel>): Flow<List<PublisherModel>> = flow {
        val expression = PublisherSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            PublisherEntity.selectAll().where { expression }.map { PublisherMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}