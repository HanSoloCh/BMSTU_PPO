package com.example.libraryapp.domain.model

import java.time.Year
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class BookModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val title: String,
    val annotation: String?,
    val publisherId: Uuid,
    val publicationYear: Int?,
    val codeISBN: String?,
    val bbkId: Uuid,
    val mediaType: String?,
    val volume: String?,
    val language: String?,
    val originalLanguage: String?,
    val copies: Int,
    val availableCopies: Int,
) {
    init {
        require(title.isNotBlank())
        require(annotation?.isNotBlank() == true)
        require(publicationYear in 0..Year.now().value)
        require(codeISBN == null || codeISBN.isNotBlank())
        require(mediaType == null || mediaType.isNotBlank())
        require(volume == null || volume.isNotBlank())
        require(language == null || language.isNotBlank())
        require(originalLanguage == null || originalLanguage.isNotBlank())
        require(copies >= 0)
        require(availableCopies in 0..copies)
    }
}