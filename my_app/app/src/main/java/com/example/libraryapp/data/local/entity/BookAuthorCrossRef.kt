package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.sql.Table



object BookAuthorCrossRef : Table("book_author") {
    val bookId = reference("book_id", BookEntity)
    val authorId = reference("author_id", AuthorEntity)

    override val primaryKey = PrimaryKey(bookId, authorId)
}
