package com.example.libraryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "book",
    foreignKeys = [
        ForeignKey(
            entity = PublisherEntity::class,
            parentColumns = ["id"],
            childColumns = ["publisher_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = BbkEntity::class,
            parentColumns = ["id"],
            childColumns = ["bbk_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val title: String,
    val annotation: String?,
    @ColumnInfo(name = "publisher_id") val publisherId: Uuid,
    @ColumnInfo(name = "publication_year") val publicationYear: Int?,
    @ColumnInfo(name = "ISBN") val codeISBN: String?,
    @ColumnInfo(name = "bbk_id") val bbkId: Uuid,
    @ColumnInfo(name = "media_type") val mediaType: String?,
    @ColumnInfo(name = "volume") val volume: String?,
    val language: String?,
    @ColumnInfo(name = "original_language") val originalLanguage: String?,
    val copies: Int,
    @ColumnInfo(name = "available_copies") val availableCopies: Int
)