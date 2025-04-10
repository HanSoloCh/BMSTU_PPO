package com.example.libraryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "publisher")
data class PublisherEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val name: String,
    val description: String?,
    @ColumnInfo(name = "foundation_year") val foundationYear: Int?,
    val email: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
)
