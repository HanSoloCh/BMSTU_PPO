package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.domain.model.AuthorModel
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow.toAuthorModel(): AuthorModel {
    return AuthorModel(
        id = this[AuthorEntity.id].value,
        name = this[AuthorEntity.name]
    )
}

fun AuthorModel.toInsertStatement(statement: InsertStatement<Number>): InsertStatement<Number> {
    return statement.also {
        it[AuthorEntity.name] = this.name
    }
}

fun AuthorModel.toUpdateStatement(statement: UpdateStatement): UpdateStatement {
    return statement.also {
        it[AuthorEntity.name] = this.name
    }
}