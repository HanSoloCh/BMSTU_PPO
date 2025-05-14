package com.example.app

import com.example.app.di.appModule
import com.example.app.plugins.configureSerialization
import com.example.app.plugins.configureStatusPages
import com.example.app.route.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
    }
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
//    configureSerialization()
//    configureStatusPages()
    routing {
        apuRoutes()
        authorRoutes()
        bbkRoutes()
        bookRoutes()
        publisherRoutes()
        userRoutes()
        reservationRoutes()
        issuanceRoutes()
        favoriteRoutes()
    }
}

