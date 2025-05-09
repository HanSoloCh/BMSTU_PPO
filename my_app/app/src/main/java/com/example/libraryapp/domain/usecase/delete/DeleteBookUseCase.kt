package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.BookRepository
import java.util.*

class DeleteBookUseCase(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookId: UUID) {
        bookRepository.deleteById(bookId)
    }
}