package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.PublisherModel
import com.example.domain.usecase.publisher.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.publisherRoutes() {
    val readPublisherByIdUseCase by inject<ReadPublisherByIdUseCase>()
    val createPublisherUseCase by inject<CreatePublisherUseCase>()
    val updatePublisherUseCase by inject<UpdatePublisherUseCase>()
    val deletePublisherUseCase by inject<DeletePublisherUseCase>()

    route("/publisher") {
        post {
            val publisher = call.receive<PublisherModel>()
            val createdId = createPublisherUseCase(publisher)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val publisher = call.receive<PublisherModel>()
            updatePublisherUseCase(publisher)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            get {
                val publisherId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readPublisherByIdUseCase(publisherId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Publisher not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val publisherId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deletePublisherUseCase(publisherId)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        route("/by-name") {
            val readPublisherByNameUseCase: ReadPublisherByNameUseCase by inject()
            get {
                val name = call.getParam<String>("name", true) { it }!!
                val publisher = readPublisherByNameUseCase(name)
                if (publisher == null) {
                    call.respond(HttpStatusCode.NotFound, "Bbk not found")
                } else {
                    call.respond(publisher)
                }
            }
        }
    }
}