package com.example.ui.network

import com.example.ui.network.dto.PublisherDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.util.*
import javax.inject.Inject

class PublisherApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getPublisher(id: UUID): PublisherDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/publisher/$id")
        return response.body()
    }
}