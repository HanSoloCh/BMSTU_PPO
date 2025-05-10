package com.example.libraryapp.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.*

@Serializable
data class IssuanceModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val bookId: @Contextual UUID,
    val userId: @Contextual UUID,
    val issuanceDate: @Contextual LocalDate,
    val returnDate: @Contextual LocalDate,
) {
    init {
        require(issuanceDate <= LocalDate.now())
        require(returnDate > issuanceDate)
    }
}
