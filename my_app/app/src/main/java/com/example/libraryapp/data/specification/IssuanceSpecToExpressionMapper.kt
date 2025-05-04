package com.example.libraryapp.data.specification

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.specification.Specification
import org.jetbrains.exposed.sql.Op

object IssuanceSpecToExpressionMapper {
    fun map(spec: Specification<IssuanceModel>): Op<Boolean> = when (spec) {
        else -> throw IllegalArgumentException("Unknown spec")
    }
}