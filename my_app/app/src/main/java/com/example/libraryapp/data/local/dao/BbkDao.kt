package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.libraryapp.data.local.entity.BbkEntity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface BbkDao {
    @Insert
    suspend fun insert(bbkEntity: BbkEntity)

    @Update
    suspend fun update(bbkEntity: BbkEntity): Int

    @Query("DELETE FROM bbk WHERE id = :id")
    suspend fun deleteById(id: Uuid): Int

    @Query("SELECT * FROM bbk WHERE id = :bbkId")
    suspend fun selectById(bbkId: Uuid): BbkEntity?
}