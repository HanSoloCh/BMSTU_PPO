package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.LanguageModel
import com.example.domain.usecase.language.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.languageRoutes() {
    val readLanguageByIdUseCase by inject<ReadLanguageByIdUseCase>()
    val createLanguageUseCase by inject<CreateLanguageUseCase>()
    val updateLanguageUseCase by inject<UpdateLanguageUseCase>()
    val deleteLanguageUseCase by inject<DeleteLanguageUseCase>()

    route("/language") {
        post {
            val language = call.receive<LanguageModel>()
            val createdId = createLanguageUseCase(language)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val language = call.receive<LanguageModel>()
            updateLanguageUseCase(language)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            get {
                val languageId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readLanguageByIdUseCase(languageId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Language not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val languageId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteLanguageUseCase(languageId)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        route("/by-name") {
            val readLanguageByNameUseCase: ReadLanguageByNameUseCase by inject()
            get {
                val code = call.getParam<String>("code", true) { it }!!
                val language = readLanguageByNameUseCase(code)
                if (language == null) {
                    call.respond(HttpStatusCode.NotFound, "Language not found")
                } else {
                    call.respond(language)
                }
            }
        }

    }
}