package com.example.ui.network

import com.example.ui.model.BookModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class BookApi(private val client: HttpClient) {
    suspend fun getAllBooks(): List<BookModel> {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/book")
        return response.body()
    }
}