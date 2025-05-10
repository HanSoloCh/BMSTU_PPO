package com.example.data.local.specification

import com.example.data.local.entity.ApuEntity
import com.example.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.apu.ApuIdSpecification
import com.example.libraryapp.domain.specification.apu.ApuTermSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like

object ApuSpecToExpressionMapper {
    fun map(spec: Specification<ApuModel>): Op<Boolean> = when (spec) {
        is ApuIdSpecification -> ApuEntity.id eq spec.id
        is ApuTermSpecification -> ApuEntity.term like spec.term

        else -> throw IllegalArgumentException("Unknown spec")
    }
}