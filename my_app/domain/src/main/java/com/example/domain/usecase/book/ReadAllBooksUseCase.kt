package com.example.domain.usecase.book

import com.example.domain.model.BookModel
import com.example.domain.repository.BookRepository
import java.util.UUID

class ReadAllBooksUseCase(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(): List<BookModel> {
        return bookRepository.readAll()
    }
}