package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.BookRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookId : Uuid) {
        bookRepository.deleteById(bookId)
    }
}