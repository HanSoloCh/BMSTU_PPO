package com.example.ui.network

import com.example.ui.network.dto.AuthorDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import java.util.UUID
import javax.inject.Inject

class PublisherApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getAuthor(id: UUID): AuthorDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/author/$id")
        return response.body()
    }
}