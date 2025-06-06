package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.BbkModel
import com.example.domain.usecase.bbk.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.bbkRoutes() {
    val readBbkByIdUseCase by inject<ReadBbkByIdUseCase>()
    val createBbkUseCase by inject<CreateBbkUseCase>()
    val updateBbkUseCase by inject<UpdateBbkUseCase>()
    val deleteBbkUseCase by inject<DeleteBbkUseCase>()

    route("/bbk") {
        post {
            val bbk = call.receive<BbkModel>()
            val createdId = createBbkUseCase(bbk)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val bbk = call.receive<BbkModel>()
            updateBbkUseCase(bbk)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            get {
                val bbkId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readBbkByIdUseCase(bbkId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Bbk not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val bbkId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteBbkUseCase(bbkId)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        route("/by-code") {
            val readBbkByCodeUseCase: ReadBbkByCodeUseCase by inject()
            get {
                val code = call.getParam<String>("code", true) { it }!!
                val bbk = readBbkByCodeUseCase(code)
                if (bbk == null) {
                    call.respond(HttpStatusCode.NotFound, "Bbk not found")
                } else {
                    call.respond(bbk)
                }
            }
        }

    }
}