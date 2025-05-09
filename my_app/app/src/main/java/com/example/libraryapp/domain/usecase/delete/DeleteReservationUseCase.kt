package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.ReservationRepository
import java.util.*

class DeleteReservationUseCase(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(reservationId: UUID) {
        reservationRepository.deleteById(reservationId)
    }
}