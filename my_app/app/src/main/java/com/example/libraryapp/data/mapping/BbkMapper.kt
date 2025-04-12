package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.domain.model.BbkModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

object BbkMapper {
    fun toDomain(row: ResultRow): BbkModel {
        return BbkModel(
            id = row[BbkEntity.id].value,
            code = row[BbkEntity.code],
            description = row[BbkEntity.description],
        )
    }

    fun toInsertStatement(bbkModel: BbkModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[BbkEntity.code] = bbkModel.code
            it[BbkEntity.description] = bbkModel.description
        }
    }

    fun toUpdateStatement(bbkModel: BbkModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[BbkEntity.code] = bbkModel.code
            it[BbkEntity.description] = bbkModel.description
        }
    }
}