package com.example.libraryapp.domain.model

import java.time.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class IssuanceModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val bookId: Uuid,
    val userId: Uuid,
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
