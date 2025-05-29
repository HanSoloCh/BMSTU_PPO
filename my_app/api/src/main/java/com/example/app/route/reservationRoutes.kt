package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.ReservationModel
import com.example.domain.usecase.reservation.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.reservationRoutes() {
    val readReservationByUserIdUseCase by inject<ReadReservationByUserIdUseCase>()
    val readReservationByBookIdUseCase by inject<ReadReservationByBookIdUseCase>()
    val createReservationUseCase by inject<CreateReservationUseCase>()
    val updateReservationUseCase by inject<UpdateReservationUseCase>()
    val deleteReservationUseCase by inject<DeleteReservationUseCase>()

    route("/reservation") {
        post {
            val reservation = call.receive<ReservationModel>()
            val createdId = createReservationUseCase(reservation)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val reservation = call.receive<ReservationModel>()
            updateReservationUseCase(reservation)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            delete {
                val reservationId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteReservationUseCase(reservationId)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        route("/user/{userId}") {
            get {
                val userId = call.getParam<UUID>("userId", true) { UUID.fromString(it) }!!
                val result = readReservationByUserIdUseCase(userId)
                call.respond(HttpStatusCode.OK, result)
            }
        }
        route("/book/{bookId}") {
            get {
                val bookId = call.getParam<UUID>("bookId", true) { UUID.fromString(it) }!!
                val result = readReservationByBookIdUseCase(bookId)
                call.respond(HttpStatusCode.OK, result)
            }
        }
    }
}