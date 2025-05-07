package com.example.libraryapp.domain.model

import java.time.LocalDate
import java.util.*

data class ReservationModel(
    val id: UUID = UUID.randomUUID(),
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
