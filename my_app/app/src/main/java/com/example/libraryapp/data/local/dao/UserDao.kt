package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.entity.ReservationEntity
import com.example.libraryapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface UserDao {
    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity): Int

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteById(id: Uuid): Int

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun selectById(userId: Uuid): UserEntity?

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun selectByEmailPassword(email: String, password: String): UserEntity?
}