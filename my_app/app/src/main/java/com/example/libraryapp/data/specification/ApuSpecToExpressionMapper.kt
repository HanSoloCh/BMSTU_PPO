package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.ApuEntity
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.apu.ApuTermSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like


object ApuSpecToExpressionMapper {
    fun map(spec: Specification<ApuModel>): Op<Boolean> = when (spec) {
        is ApuTermSpecification -> ApuEntity.term like spec.term

        else -> throw IllegalArgumentException("Unknown spec")
    }
}