package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.entity.BbkTable
import com.example.libraryapp.domain.model.BbkModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.UUID

object BbkMapper {
    fun toDomain(row: ResultRow): BbkModel {
        return BbkModel(
            id = row[BbkTable.id].value,
            code = row[BbkTable.code],
            description = row[BbkTable.description],
        )
    }

    fun toInsertStatement(
        bbkModel: BbkModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[BbkTable.id] = bbkModel.id
            it[BbkTable.code] = bbkModel.code
            it[BbkTable.description] = bbkModel.description
        }
    }

    fun toUpdateStatement(bbkModel: BbkModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[BbkTable.id] = bbkModel.id
            it[BbkTable.code] = bbkModel.code
            it[BbkTable.description] = bbkModel.description
        }
    }
}