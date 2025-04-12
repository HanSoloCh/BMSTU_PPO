package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date


object IssuanceEntity : Table("issuance") {
    val bookId = reference("book_id", BookEntity)
    val userId = reference("user_id", UserEntity)
    val issuanceDate = date("issuance_date")
    val returnDate = date("return_date")
    val extensionsCount = integer("extensions_count")

    override val primaryKey = PrimaryKey(bookId, userId)
}
