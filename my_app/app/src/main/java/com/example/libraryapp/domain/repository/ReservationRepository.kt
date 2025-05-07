package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ReservationRepository {
    suspend fun create(reservationModel: ReservationModel): UUID

    suspend fun update(reservationModel: ReservationModel): Int

    suspend fun deleteById(reservationId: UUID): Int

    suspend fun isContain(spec: Specification<ReservationModel>): Boolean

    fun query(spec: Specification<ReservationModel>): Flow<List<ReservationModel>>
}