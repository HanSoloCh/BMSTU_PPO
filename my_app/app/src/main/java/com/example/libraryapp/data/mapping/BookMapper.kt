package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.entity.BookTable
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.model.BookModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.UUID

object BookMapper {
    fun toDomain(row: ResultRow, authors: List<AuthorModel> = listOf<AuthorModel>()): BookModel {
        return BookModel(
            id = row[BookTable.id].value,
            title = row[BookTable.title],
            annotation = row[BookTable.annotation],
            authors = authors.map { it.id },
            publisherId = row[BookTable.publisherId]?.value,
            publicationYear = row[BookTable.publicationYear],
            codeISBN = row[BookTable.codeISBN],
            bbkId = row[BookTable.bbkId].value,
            mediaType = row[BookTable.mediaType],
            volume = row[BookTable.volume],
            language = row[BookTable.language],
            originalLanguage = row[BookTable.originalLanguage],
            copies = row[BookTable.copies],
            availableCopies = row[BookTable.availableCopies]
        )
    }

    fun toInsertStatement(
        bookModel: BookModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[BookTable.id] = bookModel.id
            it[BookTable.title] = bookModel.title
            it[BookTable.annotation] = bookModel.annotation
            it[BookTable.publisherId] = bookModel.publisherId
            it[BookTable.publicationYear] = bookModel.publicationYear
            it[BookTable.codeISBN] = bookModel.codeISBN
            it[BookTable.bbkId] = bookModel.bbkId
            it[BookTable.mediaType] = bookModel.mediaType
            it[BookTable.volume] = bookModel.volume
            it[BookTable.language] = bookModel.language
            it[BookTable.originalLanguage] = bookModel.originalLanguage
            it[BookTable.copies] = bookModel.copies
            it[BookTable.availableCopies] = bookModel.availableCopies
        }
    }

    fun toUpdateStatement(bookModel: BookModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[BookTable.id] = bookModel.id
            it[BookTable.title] = bookModel.title
            it[BookTable.annotation] = bookModel.annotation
            it[BookTable.publisherId] = bookModel.publisherId
            it[BookTable.publicationYear] = bookModel.publicationYear
            it[BookTable.codeISBN] = bookModel.codeISBN
            it[BookTable.bbkId] = bookModel.bbkId
            it[BookTable.mediaType] = bookModel.mediaType
            it[BookTable.volume] = bookModel.volume
            it[BookTable.language] = bookModel.language
            it[BookTable.originalLanguage] = bookModel.originalLanguage
            it[BookTable.copies] = bookModel.copies
            it[BookTable.availableCopies] = bookModel.availableCopies
        }
    }
}