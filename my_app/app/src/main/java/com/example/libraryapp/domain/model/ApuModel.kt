package com.example.libraryapp.domain.model

import java.util.*

data class ApuModel(
    val id: UUID = UUID.randomUUID(),
    val term: String,
    val bbkId: UUID
) {
    init {
        require(term.isNotBlank())
    }
}
