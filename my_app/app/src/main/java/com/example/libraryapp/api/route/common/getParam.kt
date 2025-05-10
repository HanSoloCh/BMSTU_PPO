package com.example.libraryapp.api.route.common

import com.example.libraryapp.api.route.exception.MissingParametersException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import java.util.UUID

suspend fun getParam(call: ApplicationCall, param: String): String {
    val paramValue = call.parameters[param]
    if (paramValue == null) {
        throw MissingParametersException(param)
    }
    return paramValue
}

fun getId(call: ApplicationCall, param: String) {
//    val oaramId =(call, param)
}