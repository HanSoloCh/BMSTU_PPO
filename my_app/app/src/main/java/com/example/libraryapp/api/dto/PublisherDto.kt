package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.PublisherModel
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class PublisherDto(
    val id: String,
    val name: String,
    val description: String?,
    val foundationYear: Int?,
    val email: String?,
    val phoneNumber: String?
)

fun PublisherDto.toModel() = PublisherModel(
    id = UUID.fromString(id),
    name = name,
    description = description,
    foundationYear = foundationYear,
    email = email,
    phoneNumber = phoneNumber
)

fun PublisherModel.toDto() = PublisherDto(
    id = id.toString(),
    name = name,
    description = description,
    foundationYear = foundationYear,
    email = email,
    phoneNumber = phoneNumber
)
