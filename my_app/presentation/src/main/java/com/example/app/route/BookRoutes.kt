package com.example.app.route

import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.bookRoutes() {
    route("/book") {}
}