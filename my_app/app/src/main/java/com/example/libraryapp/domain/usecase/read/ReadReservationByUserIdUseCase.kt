package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.query.reservation.specification.ReservationUserIdSpecification

import com.example.libraryapp.domain.repository.ReservationRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadReservationByUserIdUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    operator fun invoke(userId: Uuid) = reservationRepository.query(ReservationUserIdSpecification(userId))
}