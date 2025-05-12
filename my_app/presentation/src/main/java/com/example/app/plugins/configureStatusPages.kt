package com.example.app.plugins

import com.example.app.exception.ConversionFailureException
import com.example.app.exception.MissingParametersException
import com.example.domain.exception.BaseDomainException
import com.example.domain.exception.BookNoAvailableCopiesException
import com.example.domain.exception.ModelDuplicateException
import com.example.domain.exception.ModelNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<MissingParametersException> { call, exception ->
            call.respond(
                HttpStatusCode.BadRequest,
                exception.message ?: MissingParametersException::class.java.canonicalName
            )
        }
        exception<ConversionFailureException> { call, exception ->
            call.respond(
                HttpStatusCode.BadRequest,
                exception.message ?: ConversionFailureException::class.java.canonicalName
            )
        }

        exception<BaseDomainException> { call, exception ->
            when (exception) {
                is ModelNotFoundException -> call.respond(HttpStatusCode.NotFound, exception.message!!)
                is ModelDuplicateException -> call.respond(HttpStatusCode.Conflict, exception.message!!)
                is BookNoAvailableCopiesException -> call.respond(HttpStatusCode.Conflict, exception.message!!)
                else -> call.respond(HttpStatusCode.BadRequest, exception.message ?: "Domain error")
            }
        }
        exception<Throwable> { call, exception ->
            call.respond(HttpStatusCode.InternalServerError, "Internal Server Error")
        }

    }
}
