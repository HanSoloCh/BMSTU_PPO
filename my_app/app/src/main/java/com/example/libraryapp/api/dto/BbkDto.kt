package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.BbkModel
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BbkDto(
    val id: String,
    val code: String,
    val description: String
)

fun BbkDto.toModel() = BbkModel(
    id = UUID.fromString(id),
    code = code,
    description = description
)

fun BbkModel.toDto() = BbkDto(
    id = id.toString(),
    code = code,
    description = description
)
