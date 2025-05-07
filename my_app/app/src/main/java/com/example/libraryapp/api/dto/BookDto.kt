package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.BookModel
import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class BookDto(
    val id: String,
    val title: String,
    val annotation: String?,
    val authors: List<String>,
    val publisherId: String?,
    val publicationYear: Int?,
    val codeISBN: String?,
    val bbkId: String,
    val mediaType: String?,
    val volume: String?,
    val language: String?,
    val originalLanguage: String?,
    val copies: Int,
    val availableCopies: Int
)

fun BookDto.toModel() = BookModel(
    id = UUID.fromString(id),
    title = title,
    annotation = annotation,
    authors = authors.map { UUID.fromString(it) },
    publisherId = UUID.fromString(publisherId),
    publicationYear = publicationYear,
    codeISBN = codeISBN,
    bbkId = UUID.fromString(bbkId),
    mediaType = mediaType,
    volume = volume,
    language = language,
    originalLanguage = originalLanguage,
    copies = copies,
    availableCopies = availableCopies
)

fun BookModel.toDto() = BookDto(
    id = id.toString(),
    title = title,
    annotation = annotation,
    authors = authors.map { it.toString() },
    publisherId = publisherId.toString(),
    publicationYear = publicationYear,
    codeISBN = codeISBN,
    bbkId = bbkId.toString(),
    mediaType = mediaType,
    volume = volume,
    language = language,
    originalLanguage = originalLanguage,
    copies = copies,
    availableCopies = availableCopies
)