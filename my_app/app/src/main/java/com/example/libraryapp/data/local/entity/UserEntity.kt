package com.example.libraryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.libraryapp.domain.util.utils.UserRole
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "user")
data class UserEntity @OptIn(ExperimentalUuidApi::class) constructor(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val name: String,
    val surname: String,
    @ColumnInfo(name = "second_name") val secondName: String?,
    val password: String,
    val email: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    val role: UserRole,
)
