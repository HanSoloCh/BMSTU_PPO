package com.example.domain.model

import java.util.*

internal object TestAuthor {
    fun create(
        id: UUID = UUID.randomUUID(),
        name: String = "Test Author",
    ) = AuthorModel(
        id = id,
        name = name
    )
}
