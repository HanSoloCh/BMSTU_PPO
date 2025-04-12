package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.specification.book.BookPublisherIdSpecification
import com.example.libraryapp.domain.repository.BookRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ReadBooksByPublisherUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(publisherId: Uuid) = bookRepository.query(BookPublisherIdSpecification(publisherId))
}