package com.example.app.di

import DatabaseConfig
import com.example.data.local.repository.*
import com.example.domain.repository.ApuRepository
import com.example.domain.repository.AuthorRepository
import com.example.domain.repository.BbkRepository
import com.example.domain.repository.BookRepository
import com.example.domain.repository.PublisherRepository
import com.example.domain.usecase.apu.CreateApuUseCase
import com.example.domain.usecase.apu.DeleteApuUseCase
import com.example.domain.usecase.apu.ReadApuByIdUseCase
import com.example.domain.usecase.author.CreateAuthorUseCase
import com.example.domain.usecase.author.ReadAuthorByIdUseCase
import com.example.domain.usecase.bbk.CreateBbkUseCase
import com.example.domain.usecase.book.CreateBookUseCase
import com.example.domain.usecase.book.ReadBookByBbkUseCase
import com.example.domain.usecase.book.ReadBookByIdUseCase
import com.example.domain.usecase.book.ReadBookBySentenceUseCase
import com.example.domain.usecase.publisher.UpdatePublisherUseCase
import com.example.domain.usecase.publisher.CreatePublisherUseCase
import com.example.domain.usecase.author.DeleteAuthorUseCase
import com.example.domain.usecase.bbk.DeleteBbkUseCase
import com.example.domain.usecase.book.DeleteBookUseCase
import com.example.domain.usecase.book.ReadBookByAuthorUseCase
import com.example.domain.usecase.publisher.DeletePublisherUseCase
import com.example.domain.usecase.apu.UpdateApuUseCase
import com.example.domain.usecase.author.UpdateAuthorUseCase
import com.example.domain.usecase.bbk.ReadBbkByIdUseCase
import com.example.domain.usecase.bbk.UpdateBbkUseCase
import com.example.domain.usecase.book.ReadBookByPublisherUseCase
import com.example.domain.usecase.book.UpdateBookUseCase
import com.example.domain.usecase.publisher.ReadPublisherByIdUseCase
import org.koin.dsl.module

val appModule = module {
    single { DatabaseConfig.connectToDatabase() }

    single<ApuRepository> { ApuRepositoryImpl(get()) }
    single<AuthorRepository> { AuthorRepositoryImpl(get()) }
    single<BbkRepository> { BbkRepositoryImpl(get()) }
    single<PublisherRepository> { PublisherRepositoryImpl(get()) }
    single<BookRepository> { BookRepositoryImpl(get()) }

    single { ReadAuthorByIdUseCase(get()) }
    single { CreateAuthorUseCase(get()) }
    single { UpdateAuthorUseCase(get()) }
    single { DeleteAuthorUseCase(get()) }

    single { ReadApuByIdUseCase(get()) }
    single { CreateApuUseCase(get(), get()) }
    single { UpdateApuUseCase(get(), get()) }
    single { DeleteApuUseCase(get()) }

    single { ReadBbkByIdUseCase(get()) }
    single { CreateBbkUseCase(get()) }
    single { UpdateBbkUseCase(get()) }
    single { DeleteBbkUseCase(get()) }

    single { ReadPublisherByIdUseCase(get()) }
    single { CreatePublisherUseCase(get()) }
    single { UpdatePublisherUseCase(get()) }
    single { DeletePublisherUseCase(get()) }

    single { ReadBookByIdUseCase(get()) }
    single { CreateBookUseCase(get(), get(), get(), get()) }
    single { UpdateBookUseCase(get(), get(), get(), get()) }
    single { DeleteBookUseCase(get()) }
    single { ReadBookByBbkUseCase(get()) }
    single { ReadBookByPublisherUseCase(get()) }
    single { ReadBookByAuthorUseCase(get()) }
    single { ReadBookBySentenceUseCase(get(), get()) }
}