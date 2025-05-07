package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.ReservationModel
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.*

@Serializable
data class ReservationDto(
    val id: String,
    val bookId: String,
    val userId: String,
    val reservationDate: String,
    val cancelDate: String
)

fun ReservationDto.toModel() = ReservationModel(
    id = UUID.fromString(id),
    bookId = UUID.fromString(bookId),
    userId = UUID.fromString(userId),
    reservationDate = LocalDate.parse(reservationDate),
    cancelDate = LocalDate.parse(cancelDate)
)

fun ReservationModel.toDto() = ReservationDto(
    id = id.toString(),
    bookId = bookId.toString(),
    userId = userId.toString(),
    reservationDate = reservationDate.toString(),
    cancelDate = reservationDate.toString()
)
