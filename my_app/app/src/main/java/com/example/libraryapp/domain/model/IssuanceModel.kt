package com.example.libraryapp.domain.model

import java.time.LocalDate
import java.util.UUID

data class IssuanceModel(
    val id: UUID = UUID.randomUUID(),
    val bookId: UUID,
    val userId: UUID,
    val issuanceDate: LocalDate,
    val returnDate: LocalDate,
    val extensionsCount: Int = 3,
) {
    init {
        require(issuanceDate <= LocalDate.now())
        require(returnDate > issuanceDate)
        require(extensionsCount >= 0)
    }
}
