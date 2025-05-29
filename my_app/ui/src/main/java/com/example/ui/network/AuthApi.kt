package com.example.ui.network

import com.example.ui.network.dto.LoginRequest
import com.example.ui.network.dto.UserDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class AuthApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun login(request: LoginRequest): UserDto {
        val response = client.post("http://10.0.2.2:8080/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }
}