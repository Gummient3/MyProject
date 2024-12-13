package com.example

import com.auth0.jwt.JWT
import getRandomHexColor
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.*
import io.ktor.http.*
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.network.tls.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.auth.ldap.*
import io.ktor.server.engine.*
import io.ktor.server.html.respondHtml
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.request.receive
//import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import io.ktor.websocket.*
import jdk.internal.net.http.common.Log
import java.io.InputStream
import java.time.Duration
import java.util.*
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.style
import kotlinx.html.title
import org.jetbrains.exposed.sql.*
import kotlin.random.Random


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title { +"ПРИВЕТ ГНОМ" }
                    style {
                        +"body { margin: 0; font-family: sans-serif; display: flex; justify-content: center; "
                        +   "align-items: center; min-height: 100vh; color: ${getRandomHexColor()} }"
                        +"h1 { font-size: 10vw; text-align: center; }" // 10vw - 10% ширины viewport
                    }
                }
                body {
                    h1 { +"${Random.nextInt(1, 201)}" }
                }
            }
        }




        get("/secret"){
            call.respond("у тебя большой хуй")
        }
        get("/update"){
            call.respond("update")
            try{
                Runtime.getRuntime().exec("bash /root/updateServer.sh")
            } catch (e: Exception){}




        }



    }




}
