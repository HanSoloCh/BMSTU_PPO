package com.example.libraryapp.domain.query.book.specification

import com.example.libraryapp.domain.query.book.BookSpecification

class BookTitleSpecification(private val string: String) : BookSpecification {
    override fun toSqlClause(): Pair<String, List<Any>> {
        return "title LIKE ?" to listOf(string)
    }
}