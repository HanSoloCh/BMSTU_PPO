package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.UserEntity
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.user.UserIdSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

object UserSpecToExpressionMapper {
    fun map(spec: Specification<UserModel>): Op<Boolean> = when (spec) {
        is UserIdSpecification -> UserEntity.id eq spec.id

        else -> throw IllegalArgumentException("Unknown spec")
    }
}