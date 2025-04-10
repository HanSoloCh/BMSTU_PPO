package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.libraryapp.data.local.entity.PublisherEntity
import com.example.libraryapp.data.local.entity.ReservationEntity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface PublisherDao {
    @Insert
    suspend fun insert(publisherEntity: PublisherEntity)

    @Update
    suspend fun update(publisherEntity: PublisherEntity): Int

    @Query("DELETE FROM publisher WHERE id = :id")
    suspend fun deleteById(id: Uuid)

    @Query("SELECT * FROM publisher WHERE id = :publisherId")
    suspend fun selectById(publisherId: Uuid): PublisherEntity?
}