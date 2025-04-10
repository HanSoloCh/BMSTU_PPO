package com.example.libraryapp.domain.query.book.specification

import com.example.libraryapp.domain.query.book.BookSpecification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class BookBbkIdSpecification(private val bbkId: Uuid) : BookSpecification {
    override fun toSqlClause(): Pair<String, List<Any>> {
        return "bbk_id = ?" to listOf(bbkId)
    }
}