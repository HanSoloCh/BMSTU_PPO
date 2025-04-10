//package com.example.libraryapp.usecase
//
//import com.example.libraryapp.domain.model.BookModel
//import com.example.libraryapp.domain.usecase.read.ReadBooksByAuthorUseCase
//import com.example.libraryapp.mock.BookAuthorRelation
//import com.example.libraryapp.mock.MockBookRepository
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class ReadBooksByAuthorUseCaseTest {
//
//    private lateinit var mockRepo: MockBookRepository
//    private lateinit var useCase: ReadBooksByAuthorUseCase
//
//    @Before
//    fun setUp() {
//        mockRepo = MockBookRepository()
//
//        // Добавим книги
//        mockRepo.apply {
//            books.addAll(
//                listOf(
//                    BookModel(
//                        id = 1,
//                        title = "Book A",
//                        bbkId = 1,
//                        publisherId = 1,
//                        annotation = "MOCKED_1",
//                        publicationYear = 2001,
//                        codeISBN = "MOCKED_1",
//                        mediaType = "MOCKED_1",
//                        volume = "MOCKED_1",
//                        language = "MOCKED_1",
//                        originalLanguage = "MOCKED_1",
//                        copies = 1,
//                        availableCopies = 1
//                    ),
//                    BookModel(
//                        id = 2,
//                        title = "Book B",
//                        bbkId = 2,
//                        publisherId = 20,
//                        annotation = "MOCKED_2",
//                        publicationYear = 2002,
//                        codeISBN = "MOCKED_2",
//                        mediaType = "MOCKED_2",
//                        volume = "MOCKED_2",
//                        language = "MOCKED_2",
//                        originalLanguage = "MOCKED_2",
//                        copies = 2,
//                        availableCopies = 2
//                    ),
//                    BookModel(
//                        id = 3,
//                        title = "Book C",
//                        bbkId = 3,
//                        publisherId = 30,
//                        annotation = "MOCKED_3",
//                        publicationYear = 2003,
//                        codeISBN = "MOCKED_3",
//                        mediaType = "MOCKED_3",
//                        volume = "MOCKED_3",
//                        language = "MOCKED_3",
//                        originalLanguage = "MOCKED_3",
//                        copies = 3,
//                        availableCopies = 3
//                    )
//                )
//            )
//            bookAuthors.addAll(
//                listOf(
//                    BookAuthorRelation(bookId = 1, authorId = 100),
//                    BookAuthorRelation(bookId = 2, authorId = 100),
//                    BookAuthorRelation(bookId = 3, authorId = 200)
//                )
//            )
//        }
//
//        useCase = ReadBooksByAuthorUseCase(mockRepo)
//    }
//
//    @Test
//    fun `should return books for a given author`() = runTest {
//        val result = useCase(100).first()
//
//        assertEquals(2, result.size)
//        assertTrue(result.any { it.id == 1 })
//        assertTrue(result.any { it.id == 2 })
//    }
//
//    @Test
//    fun `should return empty list for unknown author`() = runTest {
//        val result = useCase(999).first()
//        assertTrue(result.isEmpty())
//    }
//
//    @Test
//    fun `should return single book for author with one book`() = runTest {
//        val result = useCase(200).first()
//
//        assertEquals(1, result.size)
//        assertEquals(3, result.first().id)
//    }
//}
