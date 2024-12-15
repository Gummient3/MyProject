package com.example.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
//import io.ktor.client.features.auth.*
//import io.ktor.client.features.auth.providers.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.http.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.internal.writeJson

object Login {
    suspend fun login() {
        // Создаем клиента с настройкой аутентификации
        val client = HttpClient(CIO) {

        }

        try {
            // Отправляем запрос на защищенный маршрут
            val response: HttpResponse = client.post("http://62.109.24.158:8080/login"){

            }

            println("Response: ${response.status}")
            println("Body: ${response.bodyAsText()}")
        } catch (e: Exception) {
            println("Error: ${e.localizedMessage}")
        } finally {
            client.close()
        }
    }

}