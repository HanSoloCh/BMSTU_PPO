package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.ReservationRepository
import javax.inject.Inject

class UpdateReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(reservationModel: ReservationModel) {
        reservationRepository.update(reservationModel)
    }
}