package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookModel: BookModel) {
        bookRepository.update(bookModel)
    }
}