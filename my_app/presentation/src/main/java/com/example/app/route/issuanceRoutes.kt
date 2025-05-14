package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.IssuanceModel
import com.example.domain.usecase.issuance.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.issuanceRoutes() {
    val readIssuanceByUserIdUseCase by inject<ReadIssuanceByUserIdUseCase>()
    val readIssuanceByBookIdUseCase by inject<ReadIssuanceByBookIdUseCase>()
    val createIssuanceUseCase by inject<CreateIssuanceUseCase>()
    val updateIssuanceUseCase by inject<UpdateIssuanceUseCase>()
    val deleteIssuanceUseCase by inject<DeleteIssuanceUseCase>()

    route("/issuance") {
        post {
            val issuance = call.receive<IssuanceModel>()
            val createdId = createIssuanceUseCase(issuance)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val issuance = call.receive<IssuanceModel>()
            updateIssuanceUseCase(issuance)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            delete {
                val issuanceId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteIssuanceUseCase(issuanceId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
        route("/user/{userId}") {
            get {
                val userId = call.getParam<UUID>("userId", true) { UUID.fromString(it) }!!
                val result = readIssuanceByUserIdUseCase(userId)
                call.respond(HttpStatusCode.OK, result)
            }
        }
        route("/book/{bookId}") {
            get {
                val bookId = call.getParam<UUID>("bookId", true) { UUID.fromString(it) }!!
                val result = readIssuanceByBookIdUseCase(bookId)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}