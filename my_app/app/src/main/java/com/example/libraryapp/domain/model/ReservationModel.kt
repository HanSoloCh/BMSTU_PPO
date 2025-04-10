package com.example.libraryapp.domain.model

import com.example.libraryapp.domain.util.utils.ReservationStatus
import java.time.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ReservationModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val bookId: Uuid,
    val userId: Uuid,
    val reservationDate: LocalDate,
    val cancelDate: LocalDate?,
    val status: ReservationStatus
) {
    init {
        require(reservationDate <= LocalDate.now())
        require(cancelDate == null || cancelDate > reservationDate)
        require(status != ReservationStatus.RESERVED || cancelDate != null)
    }
}
