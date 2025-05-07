package com.example.libraryapp.domain.model

import java.time.LocalDate
import java.util.*

data class IssuanceModel(
    val id: UUID = UUID.randomUUID(),
    val bookId: UUID,
    val userId: UUID,
    val issuanceDate: LocalDate,
    val returnDate: LocalDate,
) {
    init {
        require(issuanceDate <= LocalDate.now())
        require(returnDate > issuanceDate)
    }
}
