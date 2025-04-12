package com.example.libraryapp.domain.model

import java.time.Year
import java.util.UUID

data class BookModel(
    val id: UUID,
    val title: String,
    val annotation: String?,
    val publisherId: UUID,
    val publicationYear: Int?,
    val codeISBN: String?,
    val bbkId: UUID,
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