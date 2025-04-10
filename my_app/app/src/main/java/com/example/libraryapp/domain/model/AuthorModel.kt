package com.example.libraryapp.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class AuthorModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val name: String,
) {
    init {
        require(name.isNotBlank())
    }
}
