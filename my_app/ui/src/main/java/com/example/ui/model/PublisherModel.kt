package com.example.ui.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class PublisherModel(
    val id: @Contextual UUID,
    val name: String,
    val description: String?,
    val foundationYear: Int?,
    val email: String?,
    val phoneNumber: String?,
)