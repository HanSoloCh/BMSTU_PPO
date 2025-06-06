package com.example.data.local

import com.example.data.local.entity.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

object DatabaseBuilder {
    data class DatabaseConfig(
        val url: String,
        val driver: String,
        val username: String,
        val password: String,
        val maximumPoolSize: Int = 10
    )

    fun connect(dataSource: DataSource): Database {
        return Database.connect(dataSource)
    }

    fun createDataSource(config: DatabaseConfig): HikariDataSource {
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = config.url
            driverClassName = config.driver
            username = config.username
            password = config.password
            maximumPoolSize = config.maximumPoolSize
            connectionTimeout = 5000
            validationTimeout = 2500
        }
        return HikariDataSource(hikariConfig)
    }

    fun runMigrations(db: Database) {
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
}
