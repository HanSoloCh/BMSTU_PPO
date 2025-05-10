package com.example.libraryapp.api.route

import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.bookRoutes() {
    route("/book") {}
}