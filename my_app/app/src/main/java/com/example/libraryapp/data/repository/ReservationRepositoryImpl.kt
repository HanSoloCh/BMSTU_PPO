package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.ReservationEntity
import com.example.libraryapp.data.mapping.ReservationMapper
import com.example.libraryapp.data.specification.ReservationSpecToExpressionMapper
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.ReservationRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val db: Database
) : ReservationRepository {
    override suspend fun create(reservationModel: ReservationModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            ReservationEntity.insertAndGetId {
                ReservationMapper.toInsertStatement(reservationModel, it)
            }.value
            TODO("Нужно убавлять число книг")
        }
    }

    override suspend fun update(reservationModel: ReservationModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            ReservationEntity.update({ ReservationEntity.id eq reservationModel.id }) {
                ReservationMapper.toUpdateStatement(reservationModel, it)
            }
        }
    }

    override suspend fun deleteById(reservationId: UUID) = withContext(Dispatchers.IO) {
        transaction(db) {
            ReservationEntity.deleteWhere { id eq reservationId }
        }
    }

    override suspend fun isContain(spec: Specification<ReservationModel>) =
        withContext(Dispatchers.IO) {
            query(spec).first().isNotEmpty()
        }

    override fun query(spec: Specification<ReservationModel>): Flow<List<ReservationModel>> = flow {
        val expression = ReservationSpecToExpressionMapper.map(spec)

        val result = transaction(db) {
            ReservationEntity.selectAll().where { expression }
                .map { ReservationMapper.toDomain(it) }
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}