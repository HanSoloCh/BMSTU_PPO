package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.entity.AuthorTable
import com.example.libraryapp.domain.model.AuthorModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.UUID

object AuthorMapper {
    fun toDomain(row: ResultRow): AuthorModel {
        return AuthorModel(
            id = row[AuthorTable.id].value,
            name = row[AuthorTable.name]
        )
    }

    fun toInsertStatement(
        author: AuthorModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[AuthorTable.id] = author.id
            it[AuthorTable.name] = author.name
        }
    }

    fun toUpdateStatement(author: AuthorModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[AuthorTable.id] = author.id
            it[AuthorTable.name] = author.name
        }
    }
}