package com.example.libraryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "apu",
    foreignKeys = [
        ForeignKey(
            entity = BbkEntity::class,
            parentColumns = ["id"],
            childColumns = ["bbk_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ApuEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val term: String,
    @ColumnInfo(name = "bbk_id") val bbkId: Uuid,
)
