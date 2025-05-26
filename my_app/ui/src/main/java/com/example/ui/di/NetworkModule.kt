package com.example.ui.di

import com.example.ui.common.serializer.LocalDateSerializer
import com.example.ui.common.serializer.UUIDSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDate
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    serializersModule = SerializersModule {
                        contextual(UUID::class, UUIDSerializer)
                        contextual(LocalDate::class, LocalDateSerializer)
                    }
                })
            }
        }
    }
}