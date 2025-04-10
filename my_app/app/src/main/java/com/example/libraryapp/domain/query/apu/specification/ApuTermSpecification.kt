package com.example.libraryapp.domain.query.apu.specification

import com.example.libraryapp.domain.query.apu.ApuSpecification


class ApuTermSpecification(private val term: String) : ApuSpecification {
    override fun toSqlClause(): Pair<String, List<Any>> {
        return "term LIKE ?" to listOf(term)
    }
}