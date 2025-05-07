package com.example.libraryapp.data.specification

import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.specification.Specification
import org.jetbrains.exposed.sql.Op

object UserSpecToExpressionMapper {
    fun map(spec: Specification<UserModel>): Op<Boolean> = when (spec) {
        else -> throw IllegalArgumentException("Unknown spec")
    }
}