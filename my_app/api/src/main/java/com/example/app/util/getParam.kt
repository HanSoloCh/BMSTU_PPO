package com.example.app.util

import com.example.app.exception.ConversionFailureException
import com.example.app.exception.MissingParametersException
import io.ktor.server.application.*


//suspend fun getParam(call: ApplicationCall, param: String): String {
//    val paramValue = call.parameters[param]
//    if (paramValue == null) {
//        throw MissingParametersException(param)
//    }
//    return paramValue
//}
//
//suspend fun getId(call: ApplicationCall, param: String): UUID {
//    val paramValue = getParam(call, param)
//    val id = try {
//        UUID.fromString(paramValue)
//    } catch (e: IllegalArgumentException) {
//        throw ConversionFailureException(param)
//    }
//    return id
//}

suspend inline fun <reified T> ApplicationCall.getParam(
    param: String,
    required: Boolean = false,
    crossinline parser: (String) -> T
): T? {
    val value = parameters[param]
    return when {
        value == null && required -> throw MissingParametersException(param)
        value == null -> null
        else -> try {
            parser(value)
        } catch (e: IllegalArgumentException) {
            throw ConversionFailureException(param)
        }
    }
}