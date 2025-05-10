package com.example.data.local.specification

import com.example.data.local.entity.ReservationEntity
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.reservation.ReservationIdSpecification
import com.example.libraryapp.domain.specification.reservation.ReservationUserIdSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

object ReservationSpecToExpressionMapper {
    fun map(spec: Specification<ReservationModel>): Op<Boolean> = when (spec) {
        is ReservationIdSpecification -> ReservationEntity.id eq spec.id
        is ReservationUserIdSpecification -> ReservationEntity.userId eq spec.userId

        else -> throw IllegalArgumentException("Unknown spec")
    }
}