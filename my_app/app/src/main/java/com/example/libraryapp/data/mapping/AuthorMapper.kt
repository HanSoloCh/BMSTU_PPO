package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.domain.model.AuthorModel
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.ResultRow

object AuthorMapper {
    fun toDomain(row: ResultRow): AuthorModel {
        return AuthorModel(
            id = row[AuthorEntity.id].value,
            name = row[AuthorEntity.name]
        )
    }

    fun toInsertStatement(author: AuthorModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[AuthorEntity.name] = author.name
        }
    }

    fun toUpdateStatement(author: AuthorModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[AuthorEntity.name] = author.name
        }
    }
}