package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.IssuanceEntity
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.issuance.IssuanceIdSpecification
import com.example.libraryapp.domain.specification.issuance.IssuanceUserIdSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

object IssuanceSpecToExpressionMapper {
    fun map(spec: Specification<IssuanceModel>): Op<Boolean> = when (spec) {
        is IssuanceIdSpecification -> IssuanceEntity.id eq spec.id
        is IssuanceUserIdSpecification -> IssuanceEntity.userId eq spec.userId
        else -> throw IllegalArgumentException("Unknown spec")
    }
}