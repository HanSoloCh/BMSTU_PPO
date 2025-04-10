package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.query.reservation.ReservationSpecification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface ReservationRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun readById(reservationId: Uuid): ReservationModel?

    suspend fun create(reservationModel: ReservationModel)

    suspend fun update(reservationModel: ReservationModel)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun deleteById(reservationId: Uuid)

    fun query(specification: ReservationSpecification): Flow<List<ReservationModel>>
}