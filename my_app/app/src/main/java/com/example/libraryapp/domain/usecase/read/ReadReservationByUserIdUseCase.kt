package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.ReservationRepository
import java.util.UUID
import javax.inject.Inject

class ReadReservationByUserIdUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    operator fun invoke(userId: UUID) = reservationRepository.readByUserId(userId)
}