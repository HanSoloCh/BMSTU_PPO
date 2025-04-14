package com.example.libraryapp.domain.model

import java.time.LocalDate
import java.util.UUID

data class IssuanceModel(
    val id: UUID,
    val bookId: UUID,
    val userId: UUID,
    val issuanceDate: LocalDate,
    val returnDate: LocalDate,
    val extensionsCount: Int,
) {
    init {
        require(issuanceDate <= LocalDate.now())
        require(returnDate > issuanceDate)
        require(extensionsCount >= 0)
    }
}
