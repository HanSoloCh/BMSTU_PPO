package com.example.app.route

import com.example.app.config.JwtConfig
import com.example.app.util.LoginRequest
import com.example.domain.usecase.LoginUserUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    val loginUserUseCase: LoginUserUseCase by inject()

    post("/login") {
        val credentials = call.receive<LoginRequest>()

        val user = loginUserUseCase(credentials.email, credentials.password)

        if (user != null) {
            val token = JwtConfig.generateToken(user.id, user.role.toString())
            call.respond(token)
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Login failed")
        }
    }
}