package com.example.libraryapp.mock

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.query.reservation.ReservationSpecification
import com.example.libraryapp.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class MockReservationRepository : ReservationRepository {
    private val reservations = mutableListOf<ReservationModel>()

    override suspend fun create(reservationModel: ReservationModel) {
        reservations.add(reservationModel)
    }

    override suspend fun readById(reservationId: Uuid): ReservationModel? {
        return reservations.find { it.id == reservationId }
    }

    override suspend fun update(reservationModel: ReservationModel) {
        val index = reservations.indexOfFirst { it.id == reservationModel.id }
        if (index != -1) {
            reservations[index] = reservationModel
        }
    }

    override suspend fun deleteById(reservationId: Uuid) {
        reservations.removeAll { it.id == reservationId }
    }

    override fun query(specification: ReservationSpecification): Flow<List<ReservationModel>> {
        return flow {
            val (clause, args) = specification.toSqlClause()
            val filteredReservations = reservations.filter { reservation ->
                when (clause) {
                    "user_id = ?" -> reservation.userId == args[0]
                    else -> true
                }
            }
            emit(filteredReservations)
        }
    }
}
