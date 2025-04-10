package com.example.libraryapp.domain.query.apu

interface ApuSpecification {
    fun toSqlClause(): Pair<String, List<Any>>
}