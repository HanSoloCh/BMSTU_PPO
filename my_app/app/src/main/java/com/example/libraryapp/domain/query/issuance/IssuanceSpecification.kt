package com.example.libraryapp.domain.query.issuance

interface IssuanceSpecification {
    fun toSqlClause(): Pair<String, List<Any>>
}