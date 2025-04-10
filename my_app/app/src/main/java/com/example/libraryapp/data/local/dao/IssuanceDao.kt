package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.data.local.entity.IssuanceEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface IssuanceDao {
    @Insert
    suspend fun insert(issuanceEntity: IssuanceEntity)

    @Update
    suspend fun update(issuanceEntity: IssuanceEntity): Int

    @Query("DELETE FROM issuance WHERE id = :id")
    suspend fun deleteById(id: Uuid)

    @Query("SELECT * FROM issuance WHERE id = :issuanceId")
    suspend fun selectById(issuanceId: Uuid): IssuanceEntity?

    @RawQuery(observedEntities = [IssuanceEntity::class])
    fun select(query: SupportSQLiteQuery): Flow<List<IssuanceEntity>>
}