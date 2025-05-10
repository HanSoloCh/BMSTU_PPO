package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.repository.BookRepository
import java.util.*

class ReadBookByIdUseCase(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookId: UUID): BookModel? {
        return bookRepository.readById(bookId)
    }
}