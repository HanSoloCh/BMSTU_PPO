package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.sql.Table

object UserFavoriteCrossRef : Table("user_book") {
    val userId = reference("user_id", UserEntity)
    val bookId = reference("book_id", BookEntity)

    override val primaryKey = PrimaryKey(userId, bookId)
}


