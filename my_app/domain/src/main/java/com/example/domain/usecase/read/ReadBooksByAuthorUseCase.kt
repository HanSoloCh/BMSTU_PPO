package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.BookRepository
import java.util.UUID

class ReadBooksByAuthorUseCase(
    private val bookRepository: BookRepository
) {
    operator fun invoke(authorId: UUID) {
        TODO()
    }
}