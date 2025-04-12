package com.example.libraryapp.domain.model

import java.util.UUID

data class AuthorModel(
    val id: UUID,
    val name: String,
) {
    init {
        require(name.isNotBlank())
    }
}
