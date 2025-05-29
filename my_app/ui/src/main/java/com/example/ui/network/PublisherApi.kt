package com.example.ui.network

import com.example.ui.network.dto.PublisherDto
import com.example.ui.screens.add_entity.form.book_form.BookForm
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.net.URLEncoder
import java.util.*
import javax.inject.Inject

class PublisherApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getPublisher(id: UUID): PublisherDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/publisher/$id")
        return response.body()
    }

    suspend fun getPublisher(name: String): PublisherDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/publisher/by-name") {
            parameter("name", name)
        }
        return response.body()
    }

    suspend fun createPublisher(publisherDto: PublisherDto) {
        client.post("http://10.0.2.2:8080/publisher") {
            contentType(ContentType.Application.Json)
            setBody(publisherDto)
        }
    }
}