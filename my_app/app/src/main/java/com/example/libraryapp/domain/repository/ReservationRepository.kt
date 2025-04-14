package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ReservationModel
import java.util.UUID

interface ReservationRepository {
//    suspend fun readById(reservationId: UUID): ReservationModel?

    suspend fun create(reservationModel: ReservationModel)

    suspend fun update(reservationModel: ReservationModel): Int
//
    suspend fun deleteById(reservationId: UUID): Int

//    fun query(specification: Specification<ReservationModel>): Flow<List<ReservationModel>>
}