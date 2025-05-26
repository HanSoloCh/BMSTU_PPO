package com.example.ui.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BbkDto(
    val id: @Contextual UUID,
    val code: String,
    val description: String,
)