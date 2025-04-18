package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.ReservationRepository
import javax.inject.Inject

/**
 * Create reservation must decrement available copies
 */
class CreateReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(reservationModel: ReservationModel) {
        TODO("Add book and user check")
        reservationRepository.create(reservationModel)
    }
}