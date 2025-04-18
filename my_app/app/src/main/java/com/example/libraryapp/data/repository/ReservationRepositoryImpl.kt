package com.example.libraryapp.data.repository

import com.example.libraryapp.data.entity.ReservationEntity
import com.example.libraryapp.data.mapping.ReservationMapper
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

class ReservationRepositoryImpl @Inject constructor(
    private val db: Database
) : ReservationRepository {
    override suspend fun create(reservationModel: ReservationModel) = withContext(Dispatchers.IO) {
        transaction(db) {
            ReservationEntity.insertAndGetId {
                ReservationMapper.toInsertStatement(reservationModel, it)
            }.value
            TODO()
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

    override fun readByUserId(userId: UUID): Flow<List<ReservationModel>> = flow {
        emit(
            transaction(db) {
                ReservationEntity
                    .selectAll()
                    .where { ReservationEntity.id eq userId }
                    .map { ReservationMapper.toDomain(it) }
            }
        )
    }.flowOn(Dispatchers.IO)
}