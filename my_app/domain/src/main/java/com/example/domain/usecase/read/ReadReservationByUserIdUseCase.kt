package com.example.domain.usecase.read

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.ReservationRepository
import com.example.libraryapp.domain.specification.reservation.ReservationUserIdSpecification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ReadReservationByUserIdUseCase(
    private val reservationRepository: ReservationRepository
) {
    operator fun invoke(userId: UUID): Flow<List<ReservationModel>> {
        return reservationRepository.query(ReservationUserIdSpecification(userId))
    }
}