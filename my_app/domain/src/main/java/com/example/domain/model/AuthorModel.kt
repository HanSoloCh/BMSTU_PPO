package com.example.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AuthorModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val name: String,
) {
    init {
        require(name.isNotBlank())
    }
}
