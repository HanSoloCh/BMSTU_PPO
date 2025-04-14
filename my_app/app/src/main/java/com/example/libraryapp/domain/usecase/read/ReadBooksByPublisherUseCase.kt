package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.specification.book.BookPublisherIdSpecification
import com.example.libraryapp.domain.repository.BookRepository
import java.util.UUID
import javax.inject.Inject

class ReadBooksByPublisherUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(publisherId: UUID) = bookRepository.query(BookPublisherIdSpecification(publisherId))
}