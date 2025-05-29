package com.example.domain.model
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class LanguageModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val name: String,
)