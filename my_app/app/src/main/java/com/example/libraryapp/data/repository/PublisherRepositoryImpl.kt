package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.PublisherDao
import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.mapping.PublisherMapper
import com.example.libraryapp.data.mapping.toApuModel
import com.example.libraryapp.data.mapping.toInsertStatement
import com.example.libraryapp.data.mapping.toUpdateStatement
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class PublisherRepositoryImpl @Inject constructor(
    private val publisherDao: PublisherDao
) : PublisherRepository {
    override suspend fun readById(apuId: UUID): ApuModel? = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.select { ApuEntity.id eq apuId }.firstOrNull()?.toApuModel()
        }
    }

    override suspend fun create(apuModel: ApuModel) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.insert {
                apuModel.toInsertStatement(it)
            }
                .resultedValues?.first()?.toApuModel()?.id
                ?: throw NoSuchElementException("Error saving apu: $apuModel")
        }
    }

    override suspend fun update(apuModel: ApuModel) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.update({ ApuEntity.id eq apuModel.id }) {
                apuModel.toUpdateStatement(it)
            }
        }
    }

    override suspend fun deleteById(apuId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            ApuEntity.deleteWhere { ApuEntity.id eq apuId }
        }
    }

    override fun query(specification: Specification<ApuModel>): Flow<List<ApuModel>> {
        TODO("Not yet implemented")
    }
}