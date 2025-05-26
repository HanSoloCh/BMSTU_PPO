package com.example.ui.network

import com.example.ui.network.dto.BookDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.util.*
import javax.inject.Inject

class BookApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getBooks(page: Int = 1, pageSize: Int = 20): List<BookDto> {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/book?page=$page&pageSize=$pageSize")
        return response.body()
    }

    suspend fun getBook(id: UUID): BookDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/book/$id")
        return response.body()
    }

    // Сделать пагинацию
    suspend fun getBooksByAuthorId(authorId: UUID): List<BookDto> {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/book/search?authorId=$authorId")
        return response.body()
    }

    suspend fun getBooksByBbkId(id: UUID): List<BookDto> {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/book/search?bbkId=$id")
        return response.body()
    }

    suspend fun getBooksBySentence(sentence: String): List<BookDto> {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/book/search?q=$sentence")
        return response.body()
    }
}