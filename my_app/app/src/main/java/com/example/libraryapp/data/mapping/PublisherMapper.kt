package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.PublisherEntity
import com.example.libraryapp.domain.model.PublisherModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

object PublisherMapper {
    fun toDomain(row: ResultRow): PublisherModel {
        return PublisherModel(
            id = row[PublisherEntity.id].value,
            name = row[PublisherEntity.name],
            description = row[PublisherEntity.name],
            foundationYear = row[PublisherEntity.foundationYear],
            email = row[PublisherEntity.email],
            phoneNumber = row[PublisherEntity.phoneNumber]
        )
    }

    fun toInsertStatement(publisherModel: PublisherModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[PublisherEntity.name] = publisherModel.name
            it[PublisherEntity.description] = publisherModel.description
            it[PublisherEntity.foundationYear] = publisherModel.foundationYear
            it[PublisherEntity.email] = publisherModel.email
            it[PublisherEntity.phoneNumber] = publisherModel.phoneNumber
        }
    }

    fun toUpdateStatement(publisherModel: PublisherModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[PublisherEntity.name] = publisherModel.name
            it[PublisherEntity.description] = publisherModel.description
            it[PublisherEntity.foundationYear] = publisherModel.foundationYear
            it[PublisherEntity.email] = publisherModel.email
            it[PublisherEntity.phoneNumber] = publisherModel.phoneNumber
        }
    }
}