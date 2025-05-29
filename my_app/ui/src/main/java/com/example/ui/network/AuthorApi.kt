package com.example.ui.network

import com.example.ui.network.dto.AuthorDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.util.*
import javax.inject.Inject

class AuthorApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getAuthor(id: UUID): AuthorDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/author/$id")
        return response.body()
    }

    suspend fun getAuthor(name: String): AuthorDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/author/by-name") {
            parameter("name", name)
        }
        return response.body()
    }

    suspend fun createAuthor(authorDto: AuthorDto) {
        client.post("http://10.0.2.2:8080/author") {
            contentType(ContentType.Application.Json)
            setBody(authorDto)
        }
    }
}