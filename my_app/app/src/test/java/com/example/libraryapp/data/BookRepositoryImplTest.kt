package com.example.libraryapp.data

import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.data.local.entity.PublisherEntity
import com.example.libraryapp.data.repository.BookRepositoryImpl
import com.example.libraryapp.domain.model.BookModel
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import org.junit.Assert.*

class BookRepositoryImplTest : BasePostgresIntegrationTest() {

    private val repository = BookRepositoryImpl()
    private lateinit var authorId: UUID
    private lateinit var publisherId: UUID
    private lateinit var bbkId: UUID

    @Before
    fun setupTest() {
        setUpDatabase()
        transaction(db) {
            authorId = AuthorEntity.insertAndGetId {
                it[name] = "Test Author"
            }.value
            publisherId = PublisherEntity.insertAndGetId {
                it[name] = "Test Publisher"
            }.value
            bbkId = BbkEntity.insertAndGetId {
                it[code] = "Test code bbk"
                it[description] = "Test desc"
            }.value
        }
    }


    @After
    fun tearDown() {
        tearDownDatabase()
    }

    @Test
    fun createBookTest() = runTest {
        val newBookId = UUID.randomUUID()
        val book = BookModel(
            id = newBookId,
            title = "Test Book",
            authors = listOf(authorId),
            publisherId = publisherId,
            bbkId = bbkId
        )

        val createdId = repository.create(book)
        assertEquals(newBookId, createdId)

        val found = repository.readById(createdId)
        assertNotNull(found)
        assertEquals(book, found)
    }

    @Test
    fun updateBookTest() = runTest {
        val originalBook = BookModel(
            id = UUID.randomUUID(),
            title = "Original Title",
            authors = listOf(authorId),
            publisherId = publisherId,
            bbkId = bbkId,
        )
        repository.create(originalBook)

        // Новый автор
        val newAuthorId = transaction {
            AuthorEntity.insertAndGetId {
                it[name] = "Second Author"
            }.value
        }

        val updatedBook = originalBook.copy(
            title = "Updated Title",
            authors = listOf(newAuthorId),
        )

        repository.update(updatedBook)

        val result = repository.readById(originalBook.id)!!
        assertEquals(result, updatedBook)
    }

    @Test
    fun deleteBookTest() = runTest {
        val book = BookModel(
            id = UUID.randomUUID(),
            title = "Test Book",
            authors = listOf(authorId),
            publisherId = publisherId,
            bbkId = bbkId,
        )
        repository.create(book)

        repository.deleteById(book.id)

        val result = repository.readById(book.id)
        assertNull(result)
    }

}
