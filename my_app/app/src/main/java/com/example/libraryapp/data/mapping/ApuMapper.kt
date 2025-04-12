package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.domain.model.ApuModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.UUID


object ApuMapper {
    fun toDomain(row: ResultRow): ApuModel {
        return ApuModel(
            id = row[ApuEntity.id].value,
            term = row[ApuEntity.term],
            bbkId = row[ApuEntity.bbkId].value,
        )
    }

    fun toInsertStatement(apuModel: ApuModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[ApuEntity.term] = apuModel.term
            it[ApuEntity.bbkId] = apuModel.bbkId
        }
    }

    fun toUpdateStatement(apuModel: ApuModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[ApuEntity.term] = apuModel.term
            it[ApuEntity.bbkId] = apuModel.bbkId
        }
    }
}
