package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface UserFavoriteDao {
    @Insert
    suspend fun insert(userFavoriteCrossRef: UserFavoriteCrossRef)

    @Delete
    suspend fun delete(userFavoriteCrossRef: UserFavoriteCrossRef): Int

    @Query("""
        SELECT book.* FROM favorite
        INNER JOIN book ON favorite.book_id = book.id
        WHERE user_id = :userId
    """)
    fun selectByUserId(userId: Uuid): Flow<List<BookEntity>>
}