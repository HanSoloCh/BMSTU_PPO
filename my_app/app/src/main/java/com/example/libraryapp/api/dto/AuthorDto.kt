package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.AuthorModel
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AuthorDto(
    val id: String,
    val name: String
)

fun AuthorDto.toModel() = AuthorModel(
    id = UUID.fromString(id),
    name = name
)

fun AuthorModel.toDto() = AuthorDto(
    id = id.toString(),
    name = name
)
