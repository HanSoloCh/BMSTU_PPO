package com.example.app.di

import DatabaseConfig
import com.example.data.local.repository.AuthorRepositoryImpl
import com.example.domain.usecase.read.ReadAuthorByIdUseCase
import com.example.libraryapp.domain.repository.AuthorRepository
import org.koin.dsl.module

val appModule = module {
    single { DatabaseConfig.connectToDatabase() }

    single<AuthorRepository> { AuthorRepositoryImpl(get()) }
    single { ReadAuthorByIdUseCase(get()) }
}