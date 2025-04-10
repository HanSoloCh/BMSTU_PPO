package com.example.libraryapp.mock

import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.UserFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class MockUserFavoriteRepository : UserFavoriteRepository {
    private val userFavorites = mutableListOf<UserFavoriteCrossRef>()

    override suspend fun create(userId: Uuid, bookId: Uuid) {
        userFavorites.add(UserFavoriteCrossRef(userId, bookId))
    }

    override suspend fun delete(userId: Uuid, bookId: Uuid) {
        userFavorites.removeAll { it.userId == userId && it.bookId == bookId }
    }

    override fun readByUserId(userId: Uuid): Flow<List<BookModel>> {
        TODO()
//        return flow {
//            val bookIds = userFavorites.filter { it.userId == userId }.map { it.bookId }
//            val relatedBooks = bookIds.map { bookId ->
//                BookModel(
//                    id = bookId,
//                    title = "Book $bookId",
//                    bbkId = 123,
//                    publisherId = 456,
//                    annotation = null,
//                    publicationYear = null,
//                    codeISBN = null,
//                    mediaType = null,
//                    volume = null,
//                    language = null,
//                    originalLanguage = null,
//                    copies = 3,
//                    availableCopies = 3
//                )
//            }
//            emit(relatedBooks)
//        }
    }
}
