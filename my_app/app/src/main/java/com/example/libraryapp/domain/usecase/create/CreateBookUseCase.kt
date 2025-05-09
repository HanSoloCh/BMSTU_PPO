package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.PublisherRepository
import com.example.libraryapp.domain.specification.author.AuthorIdSpecification
import com.example.libraryapp.domain.specification.bbk.BbkIdSpecification
import com.example.libraryapp.domain.specification.book.BookIdSpecification
import com.example.libraryapp.domain.specification.publicher.PublisherIdSpecification
import java.util.*

class CreateBookUseCase(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
    private val bbkRepository: BbkRepository,
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(bookModel: BookModel): UUID {
        if (bookRepository.isContain(BookIdSpecification(bookModel.id)))
            throw ModelDuplicateException("Book", bookModel.id)

        if (!bbkRepository.isContain(BbkIdSpecification(bookModel.bbkId)))
            throw ModelNotFoundException("Bbk", bookModel.bbkId)

        if (bookModel.publisherId != null && !publisherRepository.isContain(
                PublisherIdSpecification(
                    bookModel.publisherId
                )
            )
        )
            throw ModelNotFoundException("Publisher", bookModel.publisherId)

        for (author in bookModel.authors) {
            if (!authorRepository.isContain(AuthorIdSpecification(author)))
                throw ModelNotFoundException("Author", author)
        }

        return bookRepository.create(bookModel)
    }
}