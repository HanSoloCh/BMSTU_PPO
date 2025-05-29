package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.AuthorModel
import com.example.domain.usecase.author.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.authorRoutes() {
    val readAuthorByIdUseCase: ReadAuthorByIdUseCase by inject()
    val createAuthorUseCase: CreateAuthorUseCase by inject()
    val updateAuthorUseCase: UpdateAuthorUseCase by inject()
    val deleteAuthorUseCase: DeleteAuthorUseCase by inject()

    route("/author") {
        post {
            val author = call.receive<AuthorModel>()
            val createdId = createAuthorUseCase(author)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val author = call.receive<AuthorModel>()
            updateAuthorUseCase(author)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            get {
                val authorId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readAuthorByIdUseCase(authorId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Author not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val authorId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteAuthorUseCase(authorId)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        route("/by-name") {
            val readAuthorByNameUseCase: ReadAuthorByNameUseCase by inject()
            get {
                val name = call.getParam<String>("name", true) { it }!!
                val author = readAuthorByNameUseCase(name)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Author not found")
                } else {
                    call.respond(author)
                }
            }
        }
    }
}

