package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.BookRepository
import java.util.UUID
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookId : UUID) {
        bookRepository.deleteById(bookId)
    }
}