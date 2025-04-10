package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface BookDao {
    @Insert
    suspend fun insert(bookEntity: BookEntity): Long

    @Update
    suspend fun update(bookEntity: BookEntity): Int

    @OptIn(ExperimentalUuidApi::class)
    @Query("DELETE FROM book WHERE id = :id")
    suspend fun deleteById(id: Uuid): Int

    @Query("SELECT * FROM book WHERE id = :bookId")
    suspend fun selectById(bookId: Uuid): BookEntity?

    @Query("""
        SELECT book.* FROM book
        INNER JOIN book_author ON book.id = book_author.book_id
        WHERE book_author.author_id = :authorId
    """)
    fun selectByAuthorId(authorId: Uuid): Flow<List<BookEntity>>

    @Query("""
        SELECT book.* FROM book
        INNER JOIN favorite ON book.id = favorite.book_id
        WHERE favorite.user_id = :userId
    """)
    fun selectByUserFavoriteId(userId: Uuid): Flow<List<BookEntity>>

    @RawQuery(observedEntities = [BookEntity::class])
    fun select(query: SupportSQLiteQuery): Flow<List<BookEntity>>
}