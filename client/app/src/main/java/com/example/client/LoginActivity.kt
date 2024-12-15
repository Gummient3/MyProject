package com.example.client

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.client.databinding.ActivityLoginBinding
import com.example.client.databinding.LayoutBinding
import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.Json
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
//import java.io.OutputStreamWriterimport android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.*
import io.ktor.client.call.body
//import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.*
import io.ktor.client.engine.android.Android
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent
import io.ktor.http.contentType
//import io.ktor.http.ContentType
//import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import kotlinx.serialization.Serializable
//import kotlinx.serialization.json.Json
import java.net.HttpURLConnection

class LoginActivity : AppCompatActivity() {

    private val client = HttpClient() {

    }
    lateinit var binding: LayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                sendLoginRequest(username, password)
            }
        }




    }
    //@Serializable
    data class LoginRequest(val username: String, val password: String){

    }

    private suspend fun sendLoginRequest(username: String, password: String) {
        //val loginRequest = LoginRequest(username, password)

        try {
            Log.d("Debug", "Отправляем запрос...")

            val response: HttpResponse = client.post("http://62.109.24.158:8080/registration") {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                    {
                        "username": "$username",
                        "password": "$password"
                    }
                    """.trimIndent()
                )
            }

            Log.d("Debug", "Ответ получен. Статус: ${response.status.value}")
            Log.d("Debug", "Тело ответа: ${response.bodyAsText()}")

            withContext(Dispatchers.Main) {
                when (response.status.value) {
                    200 -> {
                        Toast.makeText(this@LoginActivity, "ВЫ АВТОРИЗОВАНЫ", Toast.LENGTH_SHORT).show()
                    }
                    401 -> {
                        Toast.makeText(this@LoginActivity, "WRONG CREDS", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this@LoginActivity, "Неизвестная ошибка", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        } catch (e: Exception) {
            Log.e("Debug", "Ошибка: ${e.message}")
            e.printStackTrace()


        }
    }



}