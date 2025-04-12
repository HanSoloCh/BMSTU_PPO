package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.domain.model.BookModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

object BookMapper {
    fun toDomain(row: ResultRow): BookModel {
        TODO()
    }

    fun toInsertStatement(bookModel: BookModel, statement: InsertStatement<Number>): InsertStatement<Number> {
        return statement.also {
            it[BookEntity.title] = bookModel.title
            it[BookEntity.annotation] = bookModel.annotation
            it[BookEntity.publisherId] = bookModel.publisherId
            it[BookEntity.publicationYear] = bookModel.publicationYear
            it[BookEntity.codeISBN] = bookModel.codeISBN
            it[BookEntity.bbkId] = bookModel.bbkId
            it[BookEntity.mediaType] = bookModel.mediaType
            it[BookEntity.volume] = bookModel.volume
            it[BookEntity.language] = bookModel.language
            it[BookEntity.originalLanguage] = bookModel.originalLanguage
            it[BookEntity.copies] = bookModel.copies
            it[BookEntity.availableCopies] = bookModel.availableCopies
        }
    }

    fun toUpdateStatement(bookModel: BookModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[BookEntity.title] = bookModel.title
            it[BookEntity.annotation] = bookModel.annotation
            it[BookEntity.publisherId] = bookModel.publisherId
            it[BookEntity.publicationYear] = bookModel.publicationYear
            it[BookEntity.codeISBN] = bookModel.codeISBN
            it[BookEntity.bbkId] = bookModel.bbkId
            it[BookEntity.mediaType] = bookModel.mediaType
            it[BookEntity.volume] = bookModel.volume
            it[BookEntity.language] = bookModel.language
            it[BookEntity.originalLanguage] = bookModel.originalLanguage
            it[BookEntity.copies] = bookModel.copies
            it[BookEntity.availableCopies] = bookModel.availableCopies
        }
    }
}