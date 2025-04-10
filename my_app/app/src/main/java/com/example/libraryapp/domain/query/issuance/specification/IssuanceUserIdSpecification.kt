package com.example.libraryapp.domain.query.issuance.specification

import com.example.libraryapp.domain.query.issuance.IssuanceSpecification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
class IssuanceUserIdSpecification(private val userId: Uuid) : IssuanceSpecification {
    override fun toSqlClause(): Pair<String, List<Any>> {
        return "user_id" to listOf(userId)
    }
}