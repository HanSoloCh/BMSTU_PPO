package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.book.BookPublisherIdSpecification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ReadBooksByPublisherUseCase(
    private val bookRepository: BookRepository
) {
    operator fun invoke(publisherId: UUID): Flow<List<BookModel>> {
        return bookRepository.query(BookPublisherIdSpecification(publisherId))
    }
}