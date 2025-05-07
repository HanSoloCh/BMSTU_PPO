package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.util.utils.UserRole
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val surname: String,
    val secondName: String?,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val role: String
)

fun UserDto.toModel() = UserModel(
    id = UUID.fromString(id),
    name = name,
    surname = surname,
    secondName = secondName,
    password = password,
    email = email,
    phoneNumber = phoneNumber,
    role = UserRole.valueOf(role)
)

fun UserModel.toDto() = UserDto(
    id = id.toString(),
    name = name,
    surname = surname,
    secondName = secondName,
    password = password,
    email = email,
    phoneNumber = phoneNumber,
    role = role.toString()
)
