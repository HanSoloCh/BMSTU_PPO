package com.example.ui.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

class AuthApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun login(request: LoginRequest): String {
        val response = client.post("http://10.0.2.2:8080/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        println("Response $response")
        println("Response ${response.body<String>()}")
        return response.body()
    }
}