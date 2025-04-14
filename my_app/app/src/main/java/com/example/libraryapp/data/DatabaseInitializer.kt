package com.example.libraryapp.data

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.data.local.entity.BookAuthorCrossRef
import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.data.local.entity.IssuanceEntity
import com.example.libraryapp.data.local.entity.PublisherEntity
import com.example.libraryapp.data.local.entity.ReservationEntity
import com.example.libraryapp.data.local.entity.UserEntity
import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseInitializer {
    fun init(dbPath: String) {
        val dbUrl = "jdbc:sqlite:$dbPath"
        Database.connect(dbUrl, driver = "org.sqlite.JDBC")

        transaction {
            SchemaUtils.create(
                ApuEntity,
                AuthorEntity,
                BbkEntity,
                BookAuthorCrossRef,
                BookEntity,
                IssuanceEntity,
                PublisherEntity,
                ReservationEntity,
                UserEntity,
                UserFavoriteCrossRef
            )
        }
    }
}