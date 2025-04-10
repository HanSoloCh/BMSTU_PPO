package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.ReservationEntity
import com.example.libraryapp.domain.model.ReservationModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object ReservationMapper {
    fun toDomain(reservationEntity: ReservationEntity): ReservationModel {
        return ReservationModel(
            id = reservationEntity.id,
            bookId = reservationEntity.bookId,
            userId = reservationEntity.userId,
            reservationDate = reservationEntity.reservationDate,
            cancelDate = reservationEntity.cancelDate,
            status = reservationEntity.status
        )
    }

    fun toData(reservationModel: ReservationModel): ReservationEntity {
        return ReservationEntity(
            id = reservationModel.id,
            bookId = reservationModel.bookId,
            userId = reservationModel.userId,
            reservationDate = reservationModel.reservationDate,
            cancelDate = reservationModel.cancelDate,
            status = reservationModel.status
        )
    }
}