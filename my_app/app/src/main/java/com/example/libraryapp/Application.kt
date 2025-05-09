package com.example.libraryapp

import com.example.libraryapp.api.route.authorRoutes
import com.example.libraryapp.api.serializer.LocalDateSerializer
import com.example.libraryapp.api.serializer.UUIDSerializer
import com.example.libraryapp.di.appModule
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import java.time.LocalDate
import java.util.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
    install(ContentNegotiation) {
        json(Json {
            serializersModule = SerializersModule {
                contextual(UUID::class, UUIDSerializer)
                contextual(LocalDate::class, LocalDateSerializer)
            }
        })
    }

    routing {
        authorRoutes()
    }
}
