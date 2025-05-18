package com.example.app.logger

import org.slf4j.LoggerFactory

fun logAction(
    logLevel: LogLevel = LogLevel.INFO,
    message: String,
) {
    val logger = LoggerFactory.getLogger("AppLogger")
    when (logLevel) {
        LogLevel.INFO -> logger.info(message)
        LogLevel.DEBUG -> logger.debug(message)
        LogLevel.WARNING -> logger.warn(message)
        LogLevel.ERROR -> logger.error(message)
    }
}