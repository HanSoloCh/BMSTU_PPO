package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object BookEntity : UUIDTable("book") {
    val title = varchar("title", 255)
    val annotation = text("annotation").nullable()
    val publisherId = reference("publisher_id", PublisherEntity, onDelete = ReferenceOption.CASCADE)
    val publicationYear = integer("publication_year").nullable()
    val codeISBN = varchar("ISBN", 20).nullable()
    val bbkId = reference("bbk_id", BbkEntity, onDelete = ReferenceOption.RESTRICT)
    val mediaType = varchar("media_type", 50).nullable()
    val volume = varchar("volume", 50).nullable()
    val language = varchar("language", 50).nullable()
    val originalLanguage = varchar("original_language", 50).nullable()
    val copies = integer("copies")
    val availableCopies = integer("available_copies")
}