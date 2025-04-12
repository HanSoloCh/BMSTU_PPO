package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ReservationRepository {
    suspend fun readById(reservationId: Uuid): ReservationModel?

    suspend fun create(reservationModel: ReservationModel)

    suspend fun update(reservationModel: ReservationModel)

    suspend fun deleteById(reservationId: Uuid)

    fun query(specification: Specification<ReservationModel>): Flow<List<ReservationModel>>
}