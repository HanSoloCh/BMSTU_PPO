package com.example.data.local.specification

import com.example.data.local.entity.PublisherEntity
import com.example.domain.model.PublisherModel
import com.example.domain.specification.AndSpecification
import com.example.domain.specification.Specification
import com.example.domain.specification.publisher.PublisherIdSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

object PublisherSpecToExpressionMapper {
    fun map(spec: Specification<PublisherModel>): Op<Boolean> = when (spec) {
        is AndSpecification<PublisherModel> -> spec.specifications.map { map(it) }.reduce { a, b -> a and b }

        is PublisherIdSpecification -> PublisherEntity.id eq spec.id
        else -> throw IllegalArgumentException("Unknown spec")
    }
}