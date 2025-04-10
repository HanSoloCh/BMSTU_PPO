package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.AuthorEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface AuthorDao {
    @Insert
    suspend fun insert(authorEntity: AuthorEntity)

    @Update
    suspend fun update(authorEntity: AuthorEntity): Int

    @Query("DELETE FROM author WHERE id = :id")
    suspend fun deleteById(id: Uuid): Int

    @Query("SELECT * FROM author WHERE id = :authorId")
    suspend fun selectById(authorId: Uuid): AuthorEntity?

    @Query(
        """
        SELECT author.* FROM author
        INNER JOIN book_author ON author.id = book_author.author_id
        WHERE book_author.book_id = :bookId
    """
    )
    fun selectByBookId(bookId: Uuid): Flow<List<AuthorEntity>>

    @Query("INSERT INTO book_author (author_id, book_id) VALUES (:authorId, :bookId)")
    suspend fun insertBookToAuthor(authorId: Uuid, bookId: Uuid)
}
