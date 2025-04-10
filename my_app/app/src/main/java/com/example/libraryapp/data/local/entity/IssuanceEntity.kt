package com.example.libraryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "issuance",
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
data class IssuanceEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    @ColumnInfo(name = "book_id") val bookId: Uuid,
    @ColumnInfo(name = "user_id") val userId: Uuid,
    @ColumnInfo(name = "issuance_date") val issuanceDate: LocalDate,
    @ColumnInfo(name = "return_date") val returnDate: LocalDate,
    @ColumnInfo(name = "extensions_count") val extensionsCount: Int
)
