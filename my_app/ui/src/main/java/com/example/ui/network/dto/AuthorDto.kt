package com.example.ui.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AuthorDto(
    val id: @Contextual UUID,
    val name: String,
)
