package com.example.app

import com.example.app.di.appModule
import com.example.app.plugins.configureSerialization
import com.example.app.plugins.configureStatusPages
import com.example.app.route.apuRoutes
import com.example.app.route.authorRoutes
import com.example.app.route.bbkRoutes
import com.example.app.route.bookRoutes
import com.example.app.route.publisherRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger


fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
    configureSerialization()
    configureStatusPages()
    routing {
        apuRoutes()
        authorRoutes()
        bbkRoutes()
        bookRoutes()
        publisherRoutes()
    }
}

