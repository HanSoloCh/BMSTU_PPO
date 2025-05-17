package com.example.app.plugins

import com.example.app.exception.ConversionFailureException
import com.example.app.exception.MissingParametersException
import com.example.app.logger.LogLevel
import com.example.app.logger.logAction
import com.example.domain.exception.BaseDomainException
import com.example.domain.exception.BookNoAvailableCopiesException
import com.example.domain.exception.EmptyStringException
import com.example.domain.exception.InvalidEmailException
import com.example.domain.exception.InvalidPhoneException
import com.example.domain.exception.ModelDuplicateException
import com.example.domain.exception.ModelNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        lateinit var infoMessage: String
        exception<MissingParametersException> { call, exception ->
            infoMessage = exception.message ?: MissingParametersException::class.java.canonicalName
            logAction(LogLevel.ERROR, infoMessage)

            call.respond(
                HttpStatusCode.BadRequest,
                infoMessage
            )
        }
        exception<ConversionFailureException> { call, exception ->
            infoMessage = exception.message ?: ConversionFailureException::class.java.canonicalName
            logAction(LogLevel.ERROR, infoMessage)
            call.respond(
                HttpStatusCode.BadRequest,
                infoMessage
            )
        }

        exception<BaseDomainException> { call, exception ->
            infoMessage = exception.message ?: "Unknown domain error"
            logAction(LogLevel.ERROR, infoMessage)
            when (exception) {
                is ModelNotFoundException -> call.respond(HttpStatusCode.NotFound, infoMessage)
                is ModelDuplicateException -> call.respond(HttpStatusCode.Conflict, infoMessage)
                is BookNoAvailableCopiesException -> call.respond(HttpStatusCode.Conflict, infoMessage)

                else -> call.respond(HttpStatusCode.BadRequest, infoMessage)
            }
        }
        exception<Throwable> { call, exception ->
            infoMessage = exception.cause?.message ?: Throwable::class.java.canonicalName
            logAction(LogLevel.ERROR, infoMessage)
            call.respond(HttpStatusCode.BadRequest, infoMessage)
        }
    }
}
