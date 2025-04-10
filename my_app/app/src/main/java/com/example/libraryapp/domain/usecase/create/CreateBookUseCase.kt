package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.PublisherRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreateBookUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
    private val bbkRepository: BbkRepository,
    private val publisherRepository: PublisherRepository
) {
//    suspend operator fun invoke(book: BookModel, authors: List<AuthorModel>) {
//        val existingAuthors = authors.map { author ->
//            authorRepository.readById(author.id) ?: throw NoSuchElementException(
//                "Author with ${author.id} id not found"
//            )
//        }
//        if (bbkRepository.readById(book.bbkId) == null) {
//            throw NoSuchElementException("Bbk with ${book.id} id not found")
//        }
//        if (publisherRepository.readById(book.publisherId) == null) {
//            throw NoSuchElementException("Publisher with ${book.publisherId} id not found")
//        }
//        val bookId = bookRepository.create(book)
//        existingAuthors.forEach { authorRepository.addBookToAuthor(it.id, bookId) }
//    }
}