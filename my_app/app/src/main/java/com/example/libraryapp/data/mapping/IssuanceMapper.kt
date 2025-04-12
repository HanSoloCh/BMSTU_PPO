package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.IssuanceEntity
import com.example.libraryapp.domain.model.IssuanceModel
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

object IssuanceMapper {
    fun toDomain(row: ResultRow): IssuanceModel {
        return IssuanceModel(
            bookId = row[IssuanceEntity.bookId].value,
            userId = row[IssuanceEntity.userId].value,
            issuanceDate = row[IssuanceEntity.issuanceDate].toJavaLocalDate(),
            returnDate = row[IssuanceEntity.returnDate].toJavaLocalDate(),
            extensionsCount = row[IssuanceEntity.extensionsCount]
        )
    }

    fun toInsertStatement(issuanceModel: IssuanceModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[IssuanceEntity.bookId] = issuanceModel.bookId
            it[IssuanceEntity.userId] = issuanceModel.userId
            it[IssuanceEntity.issuanceDate] = issuanceModel.issuanceDate.toKotlinLocalDate()
            it[IssuanceEntity.returnDate] = issuanceModel.returnDate.toKotlinLocalDate()
            it[IssuanceEntity.extensionsCount] = issuanceModel.extensionsCount
        }
    }

    fun toUpdateStatement(issuanceModel: IssuanceModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[IssuanceEntity.bookId] = issuanceModel.bookId
            it[IssuanceEntity.userId] = issuanceModel.userId
            it[IssuanceEntity.issuanceDate] = issuanceModel.issuanceDate.toKotlinLocalDate()
            it[IssuanceEntity.returnDate] = issuanceModel.returnDate.toKotlinLocalDate()
            it[IssuanceEntity.extensionsCount] = issuanceModel.extensionsCount
        }
    }
}