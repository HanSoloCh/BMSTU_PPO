package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.query.book.specification.BookPublisherIdSpecification
import com.example.libraryapp.domain.repository.BookRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReadBooksByPublisherUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(publisherId: Uuid) = bookRepository.query(BookPublisherIdSpecification(publisherId))
}