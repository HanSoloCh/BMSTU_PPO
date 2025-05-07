package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.PublisherEntity
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.publicher.PublisherIdSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

object PublisherSpecToExpressionMapper {
    fun map(spec: Specification<PublisherModel>): Op<Boolean> = when (spec) {
        is PublisherIdSpecification -> PublisherEntity.id eq spec.id
        else -> throw IllegalArgumentException("Unknown spec")
    }
}