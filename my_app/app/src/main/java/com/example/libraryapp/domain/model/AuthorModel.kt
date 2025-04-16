package com.example.libraryapp.domain.model

import java.util.UUID

data class AuthorModel(
    val id: UUID = UUID.randomUUID(),
    val name: String,
) {
    init {
        require(name.isNotBlank())
    }
}
