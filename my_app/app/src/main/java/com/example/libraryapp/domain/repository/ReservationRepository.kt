package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ReservationModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ReservationRepository {
    suspend fun create(reservationModel: ReservationModel)

    suspend fun update(reservationModel: ReservationModel): Int
//
    suspend fun deleteById(reservationId: UUID): Int

    fun readByUserId(userId: UUID): Flow<List<ReservationModel>>
}