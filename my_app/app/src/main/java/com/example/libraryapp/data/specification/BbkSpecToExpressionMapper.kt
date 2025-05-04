package com.example.libraryapp.data.specification

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.specification.Specification
import org.jetbrains.exposed.sql.Op

object BbkSpecToExpressionMapper {
    fun map(spec: Specification<BbkModel>): Op<Boolean> = when (spec) {
        else -> throw IllegalArgumentException("Unknown spec")
    }
}