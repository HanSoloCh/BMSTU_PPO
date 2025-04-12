package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.specification.reservation.ReservationUserIdSpecification

import com.example.libraryapp.domain.repository.ReservationRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ReadReservationByUserIdUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(userId: Uuid) = reservationRepository.query(ReservationUserIdSpecification(userId))
}