package com.example.libraryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.entity.ReservationEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Dao
@OptIn(ExperimentalUuidApi::class)
interface ReservationDao {
    @Insert
    suspend fun insert(reservationEntity: ReservationEntity)

    @Update
    suspend fun update(reservationEntity: ReservationEntity): Int

    @Query("DELETE FROM reservation WHERE id = :id")
    suspend fun deleteById(id: Uuid): Int

    @Query("SELECT * FROM reservation WHERE id = :reservationId")
    suspend fun selectById(reservationId: Uuid): ReservationEntity?

    @RawQuery(observedEntities = [ReservationEntity::class])
    fun select(query: SupportSQLiteQuery): Flow<List<ReservationEntity>>

    @Transaction
    suspend fun createReservation(reservationEntity: ReservationEntity) {
        insert(reservationEntity)
        decrementAvailableCopies(reservationEntity.bookId)
    }

    @Transaction
    suspend fun deleteReservation(id: Uuid) {
        selectById(id)?.let {
            incrementAvailableCopies(it.bookId)
        }
        deleteById(id)
    }

    @Query("""
        UPDATE book SET available_copies = available_copies - 1 
        WHERE id = :bookId AND available_copies > 0"""
    )
    suspend fun decrementAvailableCopies(bookId: Uuid)

    @Query("UPDATE book SET available_copies = available_copies + 1 WHERE id = :bookId")
    suspend fun incrementAvailableCopies(bookId: Uuid)
}