package com.example.libraryapp.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


data class BbkModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val code: String,
    val description: String,
) {
    init {
        require(code.isNotBlank())
        require(description.isNotBlank())
    }
}
