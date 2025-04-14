package com.example.libraryapp.data.repository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import com.example.libraryapp.data.local.entity.ReservationEntity
import com.example.libraryapp.data.mapping.ReservationMapper
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor() : ReservationRepository {
    override suspend fun create(reservationModel: ReservationModel) = withContext(Dispatchers.IO) {
        transaction {
            ReservationEntity.insert {
                ReservationMapper.toInsertStatement(reservationModel, it)
            }
                .resultedValues?.first()?.let { ReservationMapper.toDomain(it).bookId }
                ?: throw NoSuchElementException("Error saving reservation: $reservationModel")
            TODO()
        }
    }

    override suspend fun update(reservationModel: ReservationModel) = withContext(Dispatchers.IO) {
        transaction {
            ReservationEntity.update({ ReservationEntity.id eq reservationModel.id }) {
                ReservationMapper.toUpdateStatement(reservationModel, it)
            }
        }
    }

    override suspend fun deleteById(reservationId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            ReservationEntity.deleteWhere { ReservationEntity.id eq reservationId }
        }
    }
}