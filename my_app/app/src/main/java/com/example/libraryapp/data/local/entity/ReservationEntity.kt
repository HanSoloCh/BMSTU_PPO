package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ReservationEntity : Table("reservation") {
    val bookId = reference("book_id", BookEntity)
    val userId = reference("user_id", UserEntity)
    val reservationDate = date("reservation_date")
    val cancelDate = date("cancel_date")

    override val primaryKey = PrimaryKey(bookId, userId)
}
