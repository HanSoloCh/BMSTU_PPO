package com.example.ui.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class PublisherDto(
    val id: @Contextual UUID = UUID.randomUUID(),
    val name: String,
    val description: String? = null,
    val foundationYear: Int? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
)