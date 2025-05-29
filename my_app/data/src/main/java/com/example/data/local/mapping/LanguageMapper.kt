package com.example.data.local.mapping

import com.example.data.local.entity.LanguageEntity
import com.example.domain.model.LanguageModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.*

object LanguageMapper {
    fun toDomain(row: ResultRow): LanguageModel {
        return LanguageModel(
            id = row[LanguageEntity.id].value,
            name = row[LanguageEntity.name],
        )
    }

    fun toInsertStatement(
        languageModel: LanguageModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[LanguageEntity.id] = languageModel.id
            it[LanguageEntity.name] = languageModel.name
        }
    }

    fun toUpdateStatement(languageModel: LanguageModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[LanguageEntity.id] = languageModel.id
            it[LanguageEntity.name] = languageModel.name
        }
    }
}