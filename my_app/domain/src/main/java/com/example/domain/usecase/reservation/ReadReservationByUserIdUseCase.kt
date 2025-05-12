package com.example.domain.usecase.reservation

import com.example.domain.model.ReservationModel
import com.example.domain.repository.ReservationRepository
import com.example.libraryapp.domain.specification.reservation.ReservationUserIdSpecification
import java.util.*

class ReadReservationByUserIdUseCase(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(userId: UUID): List<ReservationModel> {
        return reservationRepository.query(ReservationUserIdSpecification(userId))
    }
}