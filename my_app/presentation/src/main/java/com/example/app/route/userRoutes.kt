package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.UserModel
import com.example.domain.usecase.user.CreateUserUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.ReadUserByIdUseCase
import com.example.domain.usecase.user.UpdateUserUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.userRoutes() {
    val readUserByIdUseCase by inject<ReadUserByIdUseCase>()
    val createUserUseCase by inject<CreateUserUseCase>()
    val updateUserUseCase by inject<UpdateUserUseCase>()
    val deleteUserUseCase by inject<DeleteUserUseCase>()


    route("/user") {
        post {
            val user = call.receive<UserModel>()
            val createdId = createUserUseCase(user)
            call.respond(HttpStatusCode.Created, createdId)
        }

        put {
            val user = call.receive<UserModel>()
            updateUserUseCase(user)
            call.respond(HttpStatusCode.NoContent)
        }

        route("/{id}") {
            get {
                val userId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val user = readUserByIdUseCase(userId)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                } else {
                    call.respond(user)
                }
            }
            delete {
                val userId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteUserUseCase(userId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}