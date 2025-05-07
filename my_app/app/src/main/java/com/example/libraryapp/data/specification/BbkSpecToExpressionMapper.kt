package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.BbkEntity
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.bbk.BbkCodeSpecification
import com.example.libraryapp.domain.specification.bbk.BbkIdSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like

object BbkSpecToExpressionMapper {
    fun map(spec: Specification<BbkModel>): Op<Boolean> = when (spec) {
        is BbkCodeSpecification -> BbkEntity.code like spec.code
        is BbkIdSpecification -> BbkEntity.id eq spec.id
        else -> throw IllegalArgumentException("Unknown spec")
    }
}