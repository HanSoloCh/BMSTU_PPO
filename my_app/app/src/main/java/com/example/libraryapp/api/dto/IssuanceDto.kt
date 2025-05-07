package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.IssuanceModel
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.*

@Serializable
data class IssuanceDto(
    val id: String,
    val bookId: String,
    val userId: String,
    val issuanceDate: String, // ???
    val returnDate: String
)

fun IssuanceDto.toModel() = IssuanceModel(
    id = UUID.fromString(id),
    bookId = UUID.fromString(bookId),
    userId = UUID.fromString(userId),
    issuanceDate = LocalDate.parse(issuanceDate),
    returnDate = LocalDate.parse(returnDate)
)

fun IssuanceModel.toDto() = IssuanceDto(
    id = id.toString(),
    bookId = bookId.toString(),
    userId = userId.toString(),
    issuanceDate = issuanceDate.toString(),
    returnDate = returnDate.toString()
)
