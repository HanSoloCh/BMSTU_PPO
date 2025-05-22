package com.example.ui.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BbkModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val code: String,
    val description: String,
) {
    init {
            require(code.isNotBlank())
            require(description.isNotBlank())
        }
}
