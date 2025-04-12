package com.example.libraryapp.domain.model

import java.time.LocalDate
import java.util.UUID

data class ReservationModel(
    val bookId: UUID,
    val userId: UUID,
    val reservationDate: LocalDate,
    val cancelDate: LocalDate,
) {
    init {
        require(reservationDate <= LocalDate.now())
        require(cancelDate > reservationDate)
    }
}
