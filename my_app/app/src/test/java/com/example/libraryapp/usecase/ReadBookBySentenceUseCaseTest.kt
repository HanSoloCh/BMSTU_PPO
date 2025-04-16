package com.example.libraryapp.usecase

//import com.example.libraryapp.domain.model.ApuModel
//import com.example.libraryapp.domain.model.BookModel
//import com.example.libraryapp.domain.usecase.read.ReadBookBySentenceUseCase
//import com.example.libraryapp.mock.MockBookRepository
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import com.example.libraryapp.mock.MockApuRepository

//@OptIn(ExperimentalCoroutinesApi::class)
//class ReadBookBySentenceUseCaseTest {
//
//    private lateinit var apuRepository: MockApuRepository
//    private lateinit var bookRepository: MockBookRepository
//    private lateinit var useCase: ReadBookBySentenceUseCase
//
//    @Before
//    fun setUp() {
//        apuRepository = MockApuRepository()
//        bookRepository = MockBookRepository()
//
//        bookRepository.books.addAll(
//            listOf(
//                BookModel(
//                    id = 1,
//                    title = "Kotlin Basics",
//                    bbkId = 1,
//                    publisherId = 1,
//                    annotation = "MOCKED_1",
//                    publicationYear = 2001,
//                    codeISBN = "MOCKED_1",
//                    mediaType = "MOCKED_1",
//                    volume = "MOCKED_1",
//                    language = "MOCKED_1",
//                    originalLanguage = "MOCKED_1",
//                    copies = 1,
//                    availableCopies = 1
//                ),
//                BookModel(
//                    id = 2,
//                    title = "Advanced Kotlin",
//                    bbkId = 2,
//                    publisherId = 2,
//                    annotation = "MOCKED_2",
//                    publicationYear = 2002,
//                    codeISBN = "MOCKED_2",
//                    mediaType = "MOCKED_2",
//                    volume = "MOCKED_2",
//                    language = "MOCKED_2",
//                    originalLanguage = "MOCKED_2",
//                    copies = 2,
//                    availableCopies = 2
//                )
//            )
//        )
//
//        apuRepository.apus.add(
//            ApuModel(id = 1, term = "programming", bbkId = 1)
//        )
//
//        useCase = ReadBookBySentenceUseCase(apuRepository, bookRepository)
//    }
//
//    @Test
//    fun `returns books by bbkId when APU term is found`() = runTest {
//        val result = useCase("programming").first()
//
//        assertEquals(1, result.size)
//        assertEquals("Kotlin Basics", result.first().title)
//    }
//
//    @Test
//    fun `returns books by title when APU term not found`() = runTest {
//        val result = useCase("Advanced Kotlin").first()
//
//        assertEquals(1, result.size)
//        assertEquals("Advanced Kotlin", result.first().title)
//    }
//
//    @Test
//    fun `returns empty list when nothing matches`() = runTest {
//        val result = useCase("Quantum Physics").first()
//
//        assertTrue(result.isEmpty())
//    }
//}
