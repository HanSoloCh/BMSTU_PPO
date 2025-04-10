package com.example.libraryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.libraryapp.domain.util.utils.ReservationStatus
import java.time.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "reservation",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["book_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ReservationEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    @ColumnInfo(name = "book_id") val bookId: Uuid,
    @ColumnInfo(name = "user_id") val userId: Uuid,
    @ColumnInfo(name = "reservation_date") val reservationDate: LocalDate,
    @ColumnInfo(name = "cancel_date") val cancelDate: LocalDate?,
    @ColumnInfo(name = "status") val status: ReservationStatus
)
