package com.example.libraryapp.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ApuModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val term: String,
    val bbkId: @Contextual UUID
) {
    init {
        require(term.isNotBlank())
    }
}
