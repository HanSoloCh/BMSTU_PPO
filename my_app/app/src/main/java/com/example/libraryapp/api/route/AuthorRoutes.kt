package com.example.libraryapp.api.route

import com.example.libraryapp.domain.usecase.read.ReadAuthorByIdUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.authorRoutes() {
    val readAuthorByIdUseCase: ReadAuthorByIdUseCase by inject()
    route("/author/{authorId}") {
        get {
            val idParam = call.parameters["authorId"]
            if (idParam == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing authorId")
                return@get
            }

            val authorId = try {
                UUID.fromString(idParam)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, "Invalid UUID format")
                return@get
            }

            val author = readAuthorByIdUseCase(authorId)
            if (author == null) {
                call.respond(HttpStatusCode.NotFound, "Author not found")
            } else {
                call.respond(author)
            }
        }
    }
}