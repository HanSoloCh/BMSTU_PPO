package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.ReservationRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Delete reservation and increment available copies
 */
@OptIn(ExperimentalUuidApi::class)
class DeleteReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(reservationId: Uuid) {
        reservationRepository.deleteById(reservationId)
    }
}