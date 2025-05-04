package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.PublisherRepository
import javax.inject.Inject

class CreateBookUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
    private val bbkRepository: BbkRepository,
    private val publisherRepository: PublisherRepository
) {

}