package com.example.ui.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.*

@Serializable
data class ReservationModel(
    val id: @Contextual UUID,
    val bookModel: BookModel,
    val userId: UserModel,
    val reservationDate: @Contextual LocalDate,
    val cancelDate: @Contextual LocalDate,
)