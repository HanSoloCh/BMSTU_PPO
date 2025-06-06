package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.usecase.favorite.CreateFavoriteUseCase
import com.example.domain.usecase.favorite.DeleteFavoriteUseCase
import com.example.domain.usecase.favorite.ReadFavoriteByUserIdUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import java.util.*

@Serializable
data class FavoriteResponse(
    private val userId: @Contextual UUID,
    private val bookId: @Contextual UUID
) {
    constructor(idPair: Pair<UUID, UUID>) : this(idPair.first, idPair.second)
}

fun Route.favoriteRoutes() {
    val readFavoriteByUserIdUseCase by inject<ReadFavoriteByUserIdUseCase>()
    val createFavoriteUseCase by inject<CreateFavoriteUseCase>()
    val deleteFavoriteUseCase by inject<DeleteFavoriteUseCase>()

    route("/user/{userId}/favorite/{bookId}") {
        post {
            val userId = call.getParam<UUID>("userId", true) { UUID.fromString(it) }!!
            val bookId = call.getParam<UUID>("bookId", true) { UUID.fromString(it) }!!
            val createdId = createFavoriteUseCase(userId, bookId)
            call.respond(HttpStatusCode.Created, FavoriteResponse(createdId))
        }
        delete {
            val userId = call.getParam<UUID>("userId", true) { UUID.fromString(it) }!!
            val bookId = call.getParam<UUID>("bookId", true) { UUID.fromString(it) }!!
            deleteFavoriteUseCase(userId, bookId)
            call.respond(HttpStatusCode.NoContent)
        }
    }

    route("/user/{userId}/favorite") {
        get {
            val userId = call.getParam<UUID>("userId", true) { UUID.fromString(it) }!!
            val result = readFavoriteByUserIdUseCase(userId)
            call.respond(HttpStatusCode.OK, result)
        }
    }
}