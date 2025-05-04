package com.example.libraryapp.data.specification

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import org.jetbrains.exposed.sql.Op

object ReservationSpecToExpressionMapper {
    fun map(spec: Specification<ReservationModel>): Op<Boolean> = when (spec) {
        else -> throw IllegalArgumentException("Unknown spec")
    }
}