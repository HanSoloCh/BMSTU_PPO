package com.example.data.local.mapping

import com.example.data.local.entity.BookEntity
import com.example.domain.model.AuthorModel
import com.example.domain.model.BookModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.*

object BookMapper {
    fun toDomain(row: ResultRow, authors: List<AuthorModel> = listOf<AuthorModel>()): BookModel {
        return BookModel(
            id = row[BookEntity.id].value,
            title = row[BookEntity.title],
            annotation = row[BookEntity.annotation],
            authors = authors.map { it.id },
            publisherId = row[BookEntity.publisherId]?.value,
            publicationYear = row[BookEntity.publicationYear],
            codeISBN = row[BookEntity.codeISBN],
            bbkId = row[BookEntity.bbkId].value,
            mediaType = row[BookEntity.mediaType],
            volume = row[BookEntity.volume],
            languageId = row[BookEntity.languageId]?.value,
            originalLanguageId = row[BookEntity.originalLanguageId]?.value,
            secondaryLanguageId = row[BookEntity.secondaryLanguageId]?.value,
            copies = row[BookEntity.copies],
            availableCopies = row[BookEntity.availableCopies]
        )
    }

    fun toInsertStatement(
        bookModel: BookModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[BookEntity.id] = bookModel.id
            it[BookEntity.title] = bookModel.title
            it[BookEntity.annotation] = bookModel.annotation
            it[BookEntity.publisherId] = bookModel.publisherId
            it[BookEntity.publicationYear] = bookModel.publicationYear
            it[BookEntity.codeISBN] = bookModel.codeISBN
            it[BookEntity.bbkId] = bookModel.bbkId
            it[BookEntity.mediaType] = bookModel.mediaType
            it[BookEntity.volume] = bookModel.volume
            it[BookEntity.languageId] = bookModel.languageId
            it[BookEntity.originalLanguageId] = bookModel.originalLanguageId
            it[BookEntity.secondaryLanguageId] = bookModel.secondaryLanguageId
            it[BookEntity.copies] = bookModel.copies
            it[BookEntity.availableCopies] = bookModel.availableCopies
        }
    }

    fun toUpdateStatement(bookModel: BookModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[BookEntity.id] = bookModel.id
            it[BookEntity.title] = bookModel.title
            it[BookEntity.annotation] = bookModel.annotation
            it[BookEntity.publisherId] = bookModel.publisherId
            it[BookEntity.publicationYear] = bookModel.publicationYear
            it[BookEntity.codeISBN] = bookModel.codeISBN
            it[BookEntity.bbkId] = bookModel.bbkId
            it[BookEntity.mediaType] = bookModel.mediaType
            it[BookEntity.volume] = bookModel.volume
            it[BookEntity.languageId] = bookModel.languageId
            it[BookEntity.originalLanguageId] = bookModel.originalLanguageId
            it[BookEntity.secondaryLanguageId] = bookModel.secondaryLanguageId
            it[BookEntity.copies] = bookModel.copies
            it[BookEntity.availableCopies] = bookModel.availableCopies
        }
    }
}