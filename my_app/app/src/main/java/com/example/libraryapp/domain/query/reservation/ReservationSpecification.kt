package com.example.libraryapp.domain.query.reservation

interface ReservationSpecification {
    fun toSqlClause(): Pair<String, List<Any>>
}