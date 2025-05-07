package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.AuthorEntity
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.author.AuthorIdSpecification
import com.example.libraryapp.domain.specification.author.AuthorNameSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like


object AuthorSpecToExpressionMapper {
    fun map(spec: Specification<AuthorModel>): Op<Boolean> = when (spec) {
        is AuthorIdSpecification -> AuthorEntity.id eq spec.id
        is AuthorNameSpecification -> AuthorEntity.name like spec.name

        else -> throw IllegalArgumentException("Unknown spec")
    }
}