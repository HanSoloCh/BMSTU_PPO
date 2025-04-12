package com.example.libraryapp.mock

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class MockBookRepository : BookRepository {
    val books = mutableListOf<BookModel>()
    val bookAuthors = mutableListOf<BookAuthorRelation>()

    override suspend fun create(bookModel: BookModel): Int {
        books.add(bookModel)
        return books.size + 1
    }

    override suspend fun readById(bookId: Uuid): BookModel? {
        return books.find { it.id == bookId }
    }

    override fun readByAuthorId(authorId: Uuid): Flow<List<BookModel>> {
        return flow {
            val bookIds = bookAuthors.filter { it.authorId == authorId }.map { it.bookId }
            val relatedBooks = books.filter { it.id in bookIds }
            emit(relatedBooks)
        }
    }

    override suspend fun update(bookModel: BookModel) {
        val index = books.indexOfFirst { it.id == bookModel.id }
        if (index != -1) {
            books[index] = bookModel
        }
    }

    override suspend fun deleteById(bookId: Uuid) {
        books.removeAll { it.id == bookId }
    }

    override fun query(specification: BookSpecification): Flow<List<BookModel>> {
        return flow {
            val (clause, args) = specification.toSqlClause()
            val filteredBooks = books.filter { book ->
                when (clause) {
                    "bbk_id = ?" -> book.bbkId == args[0]
                    "title LIKE ?" -> book.title.contains(args[0] as String, ignoreCase = true)
                    "publisher_id = ?" -> book.publisherId == args[0]
                    else -> true
                }
            }
            emit(filteredBooks)
        }
    }

}

data class BookAuthorRelation @OptIn(ExperimentalUuidApi::class) constructor(
    val bookId: Uuid,
    val authorId: Uuid
)
