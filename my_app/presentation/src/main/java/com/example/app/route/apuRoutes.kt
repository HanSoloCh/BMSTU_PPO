package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.ApuModel
import com.example.domain.usecase.apu.CreateApuUseCase
import com.example.domain.usecase.apu.DeleteApuUseCase
import com.example.domain.usecase.apu.ReadApuByIdUseCase
import com.example.domain.usecase.apu.UpdateApuUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.apuRoutes() {
    val readApuByIdUseCase by inject<ReadApuByIdUseCase>()
    val createApuUseCase by inject<CreateApuUseCase>()
    val updateApuUseCase by inject<UpdateApuUseCase>()
    val deleteApuUseCase by inject<DeleteApuUseCase>()

    route("/apu") {
        post {
            val apu = call.receive<ApuModel>()
            val createdId = createApuUseCase(apu)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val apu = call.receive<ApuModel>()
            updateApuUseCase(apu)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            get {
                val apuId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readApuByIdUseCase(apuId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Apu not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val apuId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteApuUseCase(apuId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}