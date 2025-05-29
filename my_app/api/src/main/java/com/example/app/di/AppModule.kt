package com.example.app.di

import com.example.data.local.DatabaseBuilder
import com.example.data.local.repository.*
import com.example.domain.repository.*
import com.example.domain.usecase.LoginUserUseCase
import com.example.domain.usecase.apu.CreateApuUseCase
import com.example.domain.usecase.apu.DeleteApuUseCase
import com.example.domain.usecase.apu.ReadApuByIdUseCase
import com.example.domain.usecase.apu.UpdateApuUseCase
import com.example.domain.usecase.author.*
import com.example.domain.usecase.bbk.*
import com.example.domain.usecase.book.*
import com.example.domain.usecase.favorite.CreateFavoriteUseCase
import com.example.domain.usecase.favorite.DeleteFavoriteUseCase
import com.example.domain.usecase.favorite.ReadFavoriteByUserIdUseCase
import com.example.domain.usecase.issuance.*
import com.example.domain.usecase.publisher.*
import com.example.domain.usecase.reservation.*
import com.example.domain.usecase.user.CreateUserUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.ReadUserByIdUseCase
import com.example.domain.usecase.user.UpdateUserUseCase
import com.typesafe.config.ConfigFactory
import org.koin.dsl.module
import javax.sql.DataSource

val appModule = module {
    single {
        val config = ConfigFactory.load()
        DatabaseBuilder.DatabaseConfig(
            url = config.getString("db.url"),
            driver = config.getString("db.driver"),
            username = config.getString("db.username"),
            password = config.getString("db.password"),
            maximumPoolSize = config.getInt("db.maxPoolSize")
        )
    }
    single<DataSource> { DatabaseBuilder.createDataSource(get()) }
    single {
        val db = DatabaseBuilder.connect(get())
        DatabaseBuilder.runMigrations(db)
        db
    }

    single<ApuRepository> { ApuRepositoryImpl(get()) }
    single<AuthorRepository> { AuthorRepositoryImpl(get()) }
    single<BbkRepository> { BbkRepositoryImpl(get()) }
    single<PublisherRepository> { PublisherRepositoryImpl(get()) }
    single<BookRepository> { BookRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ReservationRepository> { ReservationRepositoryImpl(get()) }
    single<IssuanceRepository> { IssuanceRepositoryImpl(get()) }
    single<UserFavoriteRepository> { UserFavoriteRepositoryImpl(get()) }

    // Author use case
    single { ReadAuthorByIdUseCase(get()) }
    single { CreateAuthorUseCase(get()) }
    single { UpdateAuthorUseCase(get()) }
    single { DeleteAuthorUseCase(get()) }
    single { ReadAuthorByNameUseCase(get()) }

    // Apu use case
    single { ReadApuByIdUseCase(get()) }
    single { CreateApuUseCase(get(), get()) }
    single { UpdateApuUseCase(get(), get()) }
    single { DeleteApuUseCase(get()) }

    // Bbk use case
    single { ReadBbkByIdUseCase(get()) }
    single { CreateBbkUseCase(get()) }
    single { UpdateBbkUseCase(get()) }
    single { DeleteBbkUseCase(get()) }
    single { ReadBbkByCodeUseCase(get()) }

    // Publisher use case
    single { ReadPublisherByIdUseCase(get()) }
    single { CreatePublisherUseCase(get()) }
    single { UpdatePublisherUseCase(get()) }
    single { DeletePublisherUseCase(get()) }
    single { ReadPublisherByNameUseCase(get()) }

    // User use case
    single { ReadUserByIdUseCase(get()) }
    single { CreateUserUseCase(get()) }
    single { UpdateUserUseCase(get()) }
    single { DeleteUserUseCase(get()) }
    single { LoginUserUseCase(get()) }

    // Reservation use case
    single { CreateReservationUseCase(get(), get(), get()) }
    single { UpdateReservationUseCase(get(), get(), get()) }
    single { DeleteReservationUseCase(get()) }
    single { ReadReservationByBookIdUseCase(get()) }
    single { ReadReservationByUserIdUseCase(get()) }

    // Issuance use case
    single { CreateIssuanceUseCase(get(), get(), get(), get()) }
    single { UpdateIssuanceUseCase(get(), get(), get()) }
    single { DeleteIssuanceUseCase(get()) }
    single { ReadIssuanceByBookIdUseCase(get()) }
    single { ReadIssuanceByUserIdUseCase(get()) }

    // Book use case
    single { ReadBookByIdUseCase(get()) }
    single { CreateBookUseCase(get(), get(), get(), get()) }
    single { UpdateBookUseCase(get(), get(), get(), get()) }
    single { DeleteBookUseCase(get()) }
    single { ReadBookByBbkUseCase(get()) }
    single { ReadBookByPublisherUseCase(get()) }
    single { ReadBookByAuthorUseCase(get()) }
    single { ReadBookBySentenceUseCase(get(), get()) }
    single { ReadBooksUseCase(get()) }

    // Favorite use case
    single { CreateFavoriteUseCase(get(), get(), get()) }
    single { DeleteFavoriteUseCase(get()) }
    single { ReadFavoriteByUserIdUseCase(get()) }
}