package com.example.libraryapp.domain.query.book.specification

import com.example.libraryapp.domain.query.book.BookSpecification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class BookPublisherIdSpecification(private val publisherId: Uuid) : BookSpecification {
    override fun toSqlClause(): Pair<String, List<Any>> {
        return "publisher_id = ?" to listOf(publisherId)
    }
}