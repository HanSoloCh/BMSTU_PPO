package com.example.libraryapp.di

import DatabaseConfig
import com.example.libraryapp.data.repository.AuthorRepositoryImpl
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.usecase.read.ReadAuthorByIdUseCase
import org.koin.dsl.module

val appModule = module {
    single { DatabaseConfig.connectToDatabase() }

    single<AuthorRepository> { AuthorRepositoryImpl(get()) }
    single { ReadAuthorByIdUseCase(get()) }
}