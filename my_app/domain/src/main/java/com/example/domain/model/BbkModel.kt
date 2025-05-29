package com.example.domain.model

import com.example.domain.exception.EmptyStringException
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
        when {
            code.isBlank() -> throw EmptyStringException("code")
            description.isBlank() -> throw EmptyStringException(description)
        }
    }
}
