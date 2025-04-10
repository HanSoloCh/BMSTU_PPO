package com.example.libraryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "bbk")
data class BbkEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val code: String,
    val description: String,
)
