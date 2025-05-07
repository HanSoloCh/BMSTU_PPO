package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class ReadBooksByBbkUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(bbkId: UUID): Flow<List<BookModel>> {
        return bookRepository.query(BookBbkIdSpecification(bbkId))
    }
}