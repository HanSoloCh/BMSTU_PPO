package com.example.libraryapp.domain.query.reservation.specification

import com.example.libraryapp.domain.query.reservation.ReservationSpecification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
class ReservationUserIdSpecification(private val userId: Uuid) : ReservationSpecification {
    override fun toSqlClause(): Pair<String, List<Any>> {
        return "user_id" to listOf(userId)
    }
}