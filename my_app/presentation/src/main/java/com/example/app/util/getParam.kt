package com.example.app.util

import com.example.app.exception.MissingParametersException
import io.ktor.server.application.ApplicationCall

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