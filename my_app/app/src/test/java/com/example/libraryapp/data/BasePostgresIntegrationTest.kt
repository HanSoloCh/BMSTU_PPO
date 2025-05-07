package com.example.libraryapp.data

import com.example.libraryapp.data.entity.ApuEntity
import com.example.libraryapp.data.entity.AuthorEntity
import com.example.libraryapp.data.entity.BbkEntity
import com.example.libraryapp.data.entity.BookAuthorCrossRef
import com.example.libraryapp.data.entity.BookEntity
import com.example.libraryapp.data.entity.IssuanceEntity
import com.example.libraryapp.data.entity.PublisherEntity
import com.example.libraryapp.data.entity.ReservationEntity
import com.example.libraryapp.data.entity.UserEntity
import com.example.libraryapp.data.entity.UserFavoriteCrossRef
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.testcontainers.containers.PostgreSQLContainer

abstract class BasePostgresIntegrationTest {
    companion object {
        val container = PostgreSQLContainer<Nothing>("postgres:16").apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpass")
            start()
        }
    }

    protected var db = Database.connect(
        url = container.jdbcUrl,
        driver = "org.postgresql.Driver",
        user = container.username,
        password = container.password
    )

    // Создаем БД
    init {
        transaction(db) {
            SchemaUtils.createMissingTablesAndColumns(
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

    // Чистим БД после каждого теста
    @After
    fun tearDownDatabase() {
        transaction(db) {
            ReservationEntity.deleteAll()
            IssuanceEntity.deleteAll()
            UserFavoriteCrossRef.deleteAll()
            UserEntity.deleteAll()
            AuthorEntity.deleteAll()
            BookEntity.deleteAll()
            BookAuthorCrossRef.deleteAll()
            PublisherEntity.deleteAll()
            ApuEntity.deleteAll()
            BbkEntity.deleteAll()
        }
    }
}
