package com.example.app.route

import com.example.domain.usecase.LoginUserUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

fun Route.authRoutes() {
    val loginUserUseCase: LoginUserUseCase by inject()

    post("/login") {
        val credentials = call.receive<LoginRequest>()

        val user = loginUserUseCase(credentials.email, credentials.password)

        if (user != null) {
            call.respond(user)
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Login failed")
        }
    }
}