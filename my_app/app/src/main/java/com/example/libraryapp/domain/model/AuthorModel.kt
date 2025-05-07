package com.example.libraryapp.domain.model

import java.util.*

data class AuthorModel(
    val id: UUID = UUID.randomUUID(),
    val name: String,
) {
    init {
        require(name.isNotBlank())
    }
}
