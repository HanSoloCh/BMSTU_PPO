package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.domain.model.ApuModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement


fun ResultRow.toApuModel(): ApuModel {
    return ApuModel(
        id = this[ApuEntity.id].value,
        term = this[ApuEntity.term],
        bbkId = this[ApuEntity.bbkId].value,
    )
}

fun ApuModel.toInsertStatement(statement: InsertStatement<Number>): InsertStatement<Number> {
    return statement.also {
        it[ApuEntity.term] = this.term
        it[ApuEntity.bbkId] = this.bbkId
    }
}

fun ApuModel.toUpdateStatement(statement: UpdateStatement): UpdateStatement {
    return statement.also {
        it[ApuEntity.term] = this.term
        it[ApuEntity.bbkId] = this.bbkId
    }
}
