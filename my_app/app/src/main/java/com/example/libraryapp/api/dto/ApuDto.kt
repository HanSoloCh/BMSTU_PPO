package com.example.libraryapp.api.dto

import com.example.libraryapp.domain.model.ApuModel
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ApuDto(
    val id: String,
    val term: String,
    val bbkId: String
)

fun ApuDto.toModel() = ApuModel(
    id = UUID.fromString(id),
    term = term,
    bbkId = UUID.fromString(bbkId)
)

fun ApuModel.toDto() = ApuDto(
    id = id.toString(),
    term = term,
    bbkId = bbkId.toString()
)