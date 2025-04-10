package com.example.libraryapp.domain.query.book

interface BookSpecification {
    fun toSqlClause(): Pair<String, List<Any>>
}