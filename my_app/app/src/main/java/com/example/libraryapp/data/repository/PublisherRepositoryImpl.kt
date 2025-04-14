package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.PublisherEntity
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import com.example.libraryapp.data.mapping.PublisherMapper
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class PublisherRepositoryImpl @Inject constructor() : PublisherRepository {
    override suspend fun readById(publisherId: UUID): PublisherModel? = withContext(Dispatchers.IO) {
        transaction {
            PublisherEntity.selectAll().where { PublisherEntity.id eq publisherId }.firstOrNull()?.let {
                PublisherMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(publisherModel: PublisherModel) = withContext(Dispatchers.IO) {
        transaction {
            PublisherEntity.insert {
                PublisherMapper.toInsertStatement(publisherModel, it)
            }
                .resultedValues?.first()?.let { PublisherMapper.toDomain(it).id }
                ?: throw NoSuchElementException("Error saving publisher: $publisherModel")
        }
    }

    override suspend fun update(publisherModel: PublisherModel) = withContext(Dispatchers.IO) {
        transaction {
            PublisherEntity.update({ PublisherEntity.id eq publisherModel.id }) {
                PublisherMapper.toUpdateStatement(publisherModel, it)
            }
        }
    }

    override suspend fun deleteById(publisherId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.deleteWhere { PublisherEntity.id eq publisherId }
        }
    }
}