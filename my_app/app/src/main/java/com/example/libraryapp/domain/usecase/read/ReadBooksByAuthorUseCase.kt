package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.BookRepository
import java.util.UUID
import javax.inject.Inject

class ReadBooksByAuthorUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(authorId: UUID) {
        TODO()
    }
}