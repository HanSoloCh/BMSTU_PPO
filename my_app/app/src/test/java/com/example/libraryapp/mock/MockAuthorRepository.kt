//package com.example.libraryapp.mock
//
//import com.example.libraryapp.domain.model.AuthorModel
//import com.example.libraryapp.domain.repository.AuthorRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlin.uuid.ExperimentalUuidApi
//import kotlin.uuid.Uuid
//
//@OptIn(ExperimentalUuidApi::class)
//class MockAuthorRepository : AuthorRepository {
//    val authors = mutableListOf<AuthorModel>()
//    val bookAuthors = mutableListOf<BookAuthorRelation>()
//
//    override suspend fun create(authorModel: AuthorModel) {
//        authors.add(authorModel)
//    }
//
//    override suspend fun readById(authorId: Uuid): AuthorModel? {
//        return authors.find { it.id == authorId }
//    }
//
//    override fun readByBookId(bookId: Uuid): Flow<List<AuthorModel>> {
//        return flow {
//            val authorIds = bookAuthors.filter { it.bookId == bookId }.map { it.authorId }
//            val relatedAuthors = authors.filter { it.id in authorIds }
//            emit(relatedAuthors)
//        }
//    }
//
//    override suspend fun update(authorModel: AuthorModel) {
//        val index = authors.indexOfFirst { it.id == authorModel.id }
//        if (index != -1) {
//            authors[index] = authorModel
//        }
//    }
//
//    override suspend fun deleteById(authorId: Uuid) {
//        authors.removeAll { it.id == authorId }
//    }
//
//    override suspend fun addBookToAuthor(authorId: Uuid, bookId: Uuid) {
//        bookAuthors.add(BookAuthorRelation(bookId, authorId))
//    }
//}
