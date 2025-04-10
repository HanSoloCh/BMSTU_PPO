package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.entity.ApuEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface ApuDao {
    @Insert
    suspend fun insert(apuEntity: ApuEntity)

    @Update
    suspend fun update(apuEntity: ApuEntity): Int

    @Query("DELETE FROM apu WHERE id = :id")
    suspend fun deleteById(id: Uuid): Int

    @Query("SELECT * FROM apu WHERE id = :apuId")
    suspend fun selectById(apuId: Uuid): ApuEntity?

    @RawQuery(observedEntities = [ApuEntity::class])
    fun select(query: SupportSQLiteQuery): Flow<List<ApuEntity>>
}