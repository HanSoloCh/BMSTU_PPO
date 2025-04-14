package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import com.example.libraryapp.domain.repository.BookRepository
import java.util.UUID
import javax.inject.Inject

class ReadBooksByBbkUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(bbkId: UUID) = bookRepository.query(BookBbkIdSpecification(bbkId))
}