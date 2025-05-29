package com.example.ui.network

import com.example.ui.network.dto.BookDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import java.util.*
import javax.inject.Inject

class UserApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun addToFavorite(userId: UUID, bookId: UUID) {
        client.post("http://10.0.2.2:8080/user/$userId/favorite/$bookId")
    }

    suspend fun removeFromFavorite(userId: UUID, bookId: UUID) {
        client.delete("http://10.0.2.2:8080/user/$userId/favorite/$bookId")
    }

    suspend fun getFavorite(userId: UUID): List<BookDto> {
        val response = client.get("http://10.0.2.2:8080/user/$userId/favorite")
        println(response)
        return response.body()
    }

    suspend fun getFavoriteById(id: UUID): List<BookDto> {
        val response = client.get("http://10.0.2.2:8080/user/$id/favorite")
        return response.body()
    }
}