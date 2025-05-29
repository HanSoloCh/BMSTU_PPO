package com.example.ui.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BookDto(
    val id: @Contextual UUID = UUID.randomUUID(),
    val title: String,
    val annotation: String? = null,
    val authors: List<@Contextual UUID>,
    val publisherId: @Contextual UUID? = null,
    val publicationYear: Int? = null,
    val codeISBN: String? = null,
    val bbkId: @Contextual UUID,
    val mediaType: String? = null,
    val volume: String? = null,
    val language: String? = null,
    val originalLanguage: String? = null,
    val copies: Int,
    val availableCopies: Int,
)