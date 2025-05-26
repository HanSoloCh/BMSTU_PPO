package com.example.ui.network

import com.example.ui.network.dto.BbkDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.util.*
import javax.inject.Inject

class BbkApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getBbk(id: UUID): BbkDto {
        val response: HttpResponse = client.get("http://10.0.2.2:8080/bbk/$id")
        return response.body()
    }
}