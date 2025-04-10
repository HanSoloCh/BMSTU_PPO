package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.query.book.specification.BookBbkIdSpecification
import com.example.libraryapp.domain.repository.BookRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadBooksByBbkUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(bbkId: Uuid) = bookRepository.query(BookBbkIdSpecification(bbkId))
}