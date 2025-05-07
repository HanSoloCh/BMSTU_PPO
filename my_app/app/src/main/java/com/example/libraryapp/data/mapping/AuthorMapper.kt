package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.entity.AuthorEntity
import com.example.libraryapp.domain.model.AuthorModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.*

object AuthorMapper {
    fun toDomain(row: ResultRow): AuthorModel {
        return AuthorModel(
            id = row[AuthorEntity.id].value,
            name = row[AuthorEntity.name]
        )
    }

    fun toInsertStatement(
        author: AuthorModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[AuthorEntity.id] = author.id
            it[AuthorEntity.name] = author.name
        }
    }

    fun toUpdateStatement(author: AuthorModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[AuthorEntity.id] = author.id
            it[AuthorEntity.name] = author.name
        }
    }
}