package com.example.data.local.specification

import com.example.data.local.entity.LanguageEntity
import com.example.domain.model.LanguageModel
import com.example.domain.specification.AndSpecification
import com.example.domain.specification.Specification
import com.example.domain.specification.language.LanguageIdSpecification
import com.example.domain.specification.language.LanguageNameSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.and

object LanguageSpecToExpressionMapper {
    fun map(spec: Specification<LanguageModel>): Op<Boolean> = when (spec) {
        is AndSpecification<LanguageModel> -> spec.specifications.map { map(it) }.reduce { a, b -> a and b }

        is LanguageNameSpecification -> LanguageEntity.name like spec.name
        is LanguageIdSpecification -> LanguageEntity.id eq spec.id
        else -> throw IllegalArgumentException("Unknown spec")
    }
}