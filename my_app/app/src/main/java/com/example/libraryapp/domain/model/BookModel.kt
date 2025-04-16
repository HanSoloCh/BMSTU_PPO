package com.example.libraryapp.domain.model

import java.time.Year
import java.util.UUID

data class BookModel(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val annotation: String? = null,
    val authors: List<UUID>,
    val publisherId: UUID?,
    val publicationYear: Int? = null,
    val codeISBN: String? = null,
    val bbkId: UUID,
    val mediaType: String? = null,
    val volume: String? = null,
    val language: String? = null,
    val originalLanguage: String? = null,
    val copies: Int = 0,
    val availableCopies: Int = 0,
) {
    init {
        require(title.isNotBlank())
        require(annotation == null || annotation.isNotBlank())
        require(publicationYear == null || publicationYear in 0..Year.now().value)
        require(codeISBN == null || codeISBN.isNotBlank())
        require(mediaType == null || mediaType.isNotBlank())
        require(volume == null || volume.isNotBlank())
        require(language == null || language.isNotBlank())
        require(originalLanguage == null || originalLanguage.isNotBlank())
        require(copies >= 0)
        require(availableCopies in 0..copies)
    }
}