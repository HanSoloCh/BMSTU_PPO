package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import com.example.libraryapp.domain.repository.BookRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ReadBooksByBbkUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(bbkId: Uuid) = bookRepository.query(BookBbkIdSpecification(bbkId))
}