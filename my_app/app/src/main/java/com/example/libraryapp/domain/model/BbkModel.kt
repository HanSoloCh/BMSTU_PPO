package com.example.libraryapp.domain.model

import java.util.UUID


data class BbkModel(
    val id: UUID,
    val code: String,
    val description: String,
) {
    init {
        require(code.isNotBlank())
        require(description.isNotBlank())
    }
}
