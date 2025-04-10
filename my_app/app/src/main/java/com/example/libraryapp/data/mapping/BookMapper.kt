package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.domain.model.BookModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object BookMapper {
    fun toDomain(bookEntity: BookEntity): BookModel {
        return BookModel(
            id = bookEntity.id,
            title = bookEntity.title,
            annotation = bookEntity.annotation,
            publisherId = bookEntity.publisherId,
            publicationYear = bookEntity.publicationYear,
            codeISBN = bookEntity.codeISBN,
            bbkId = bookEntity.bbkId,
            mediaType = bookEntity.mediaType,
            volume = bookEntity.volume,
            language = bookEntity.language,
            originalLanguage = bookEntity.originalLanguage,
            copies = bookEntity.copies,
            availableCopies = bookEntity.availableCopies
        )
    }

    fun toData(bookModel: BookModel): BookEntity {
        return BookEntity(
            id = bookModel.id,
            title = bookModel.title,
            annotation = bookModel.annotation,
            publisherId = bookModel.publisherId,
            publicationYear = bookModel.publicationYear,
            codeISBN = bookModel.codeISBN,
            bbkId = bookModel.bbkId,
            mediaType = bookModel.mediaType,
            volume = bookModel.volume,
            language = bookModel.language,
            originalLanguage = bookModel.originalLanguage,
            copies = bookModel.copies,
            availableCopies = bookModel.availableCopies
        )
    }
}