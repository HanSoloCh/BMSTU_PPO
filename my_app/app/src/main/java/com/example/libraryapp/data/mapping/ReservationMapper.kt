package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.ReservationEntity
import com.example.libraryapp.domain.model.ReservationModel
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

object ReservationMapper {
    fun toDomain(row: ResultRow): ReservationModel {
        return ReservationModel(
            id = row[ReservationEntity.id].value,
            bookId = row[ReservationEntity.bookId].value,
            userId = row[ReservationEntity.userId].value,
            reservationDate = row[ReservationEntity.reservationDate].toJavaLocalDate(),
            cancelDate = row[ReservationEntity.cancelDate].toJavaLocalDate(),
        )
    }

    fun toInsertStatement(reservationModel: ReservationModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[ReservationEntity.bookId] = reservationModel.bookId
            it[ReservationEntity.userId] = reservationModel.userId
            it[ReservationEntity.reservationDate] = reservationModel.reservationDate.toKotlinLocalDate()
            it[ReservationEntity.cancelDate] = reservationModel.cancelDate.toKotlinLocalDate()
        }
    }

    fun toUpdateStatement(reservationModel: ReservationModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[ReservationEntity.bookId] = reservationModel.bookId
            it[ReservationEntity.userId] = reservationModel.userId
            it[ReservationEntity.reservationDate] = reservationModel.reservationDate.toKotlinLocalDate()
            it[ReservationEntity.cancelDate] = reservationModel.cancelDate.toKotlinLocalDate()
        }
    }

}