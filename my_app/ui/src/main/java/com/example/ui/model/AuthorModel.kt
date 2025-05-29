package com.example.ui.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AuthorModel(
    val id: @Contextual UUID,
    val name: String,
)