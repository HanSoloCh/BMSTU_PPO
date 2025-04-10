package com.example.libraryapp.usecase

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.*
import com.example.libraryapp.domain.usecase.create.CreateBookUseCase
import com.example.libraryapp.mock.MockAuthorRepository
import com.example.libraryapp.mock.MockBbkRepository
import com.example.libraryapp.mock.MockBookRepository
import com.example.libraryapp.mock.MockPublisherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

//@OptIn(ExperimentalCoroutinesApi::class)
//class CreateBookUseCaseTest {
//
//    private lateinit var useCase: CreateBookUseCase
//    private lateinit var bookRepository: MockBookRepository
//    private lateinit var authorRepository: MockAuthorRepository
//    private lateinit var bbkRepository: MockBbkRepository
//    private lateinit var publisherRepository: MockPublisherRepository
//
//    private lateinit var book: BookModel
//    private lateinit var author: AuthorModel
//    private lateinit var bbk: BbkModel
//    private lateinit var publisher: PublisherModel
//
//    @Before
//    fun setUp() {
//        bookRepository = MockBookRepository()
//        authorRepository = MockAuthorRepository()
//        bbkRepository = MockBbkRepository()
//        publisherRepository = MockPublisherRepository()
//
//        // Инициализация тестовых данных
//        bbk = BbkModel(id = 100, code = "MOCK_BBK", description = "Test BBK")
//        publisher = PublisherModel(
//            name = "Test Publisher",
//            description = "Test",
//            foundationYear = 2000,
//            email = "test@test.com",
//            phoneNumber = "1234567890"
//        )
//        author = AuthorModel(id = 1, name = "Test Author")
//        book = BookModel(
//            id = 0,
//            title = "Test Book",
//            bbkId = bbk.id,
//            publisherId = publisher.id,
//            annotation = "Test annotation",
//            publicationYear = 2023,
//            codeISBN = "123-456-789",
//            mediaType = "Print",
//            volume = "300",
//            language = "English",
//            originalLanguage = "English",
//            copies = 5,
//            availableCopies = 5
//        )
//
//        // Добавление тестовых данных в моки
//        bbkRepository.bbks.add(bbk)
//        publisherRepository.mockPublishers.add(publisher)
//        authorRepository.authors.add(author)
//
//        useCase = CreateBookUseCase(
//            bookRepository,
//            authorRepository,
//            bbkRepository,
//            publisherRepository
//        )
//    }
//
//    @Test(expected = NoSuchElementException::class)
//    fun `create book with non-existent author should throw`() = runTest {
//        // Arrange
//        val nonExistentAuthor = AuthorModel(id = 999, name = "Non-existent")
//
//        // Act & Assert
//        useCase(book, listOf(nonExistentAuthor))
//    }
//
//    @Test(expected = NoSuchElementException::class)
//    fun `create book with non-existent bbk should throw`() = runTest {
//        // Arrange
//        bbkRepository.bbks.clear()
//
//        // Act & Assert
//        useCase(book, listOf(author))
//    }
//
//    @Test(expected = NoSuchElementException::class)
//    fun `create book with non-existent publisher should throw`() = runTest {
//        // Arrange
//        publisherRepository.mockPublishers.clear()
//
//        // Act & Assert
//        useCase(book, listOf(author))
//    }
//
//}