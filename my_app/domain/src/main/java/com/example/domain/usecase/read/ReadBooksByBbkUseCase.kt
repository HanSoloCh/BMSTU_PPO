package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ReadBooksByBbkUseCase(
    private val bookRepository: BookRepository
) {
    operator fun invoke(bbkId: UUID): Flow<List<BookModel>> {
        return bookRepository.query(BookBbkIdSpecification(bbkId))
    }
}