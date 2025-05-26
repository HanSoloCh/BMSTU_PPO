package com.example.ui.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ApuDto(
    val id: @Contextual UUID,
    val term: String,
    val bbkId: @Contextual UUID
)
