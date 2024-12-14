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
import com.example.JWT.auth
import com.example.TestRoutes.Test
import com.example.TestRoutes.Test.baseRoutes
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
import kotlinx.serialization.Serializable
import org.h2.engine.User
import org.jetbrains.exposed.sql.*
import kotlin.random.Random
@Serializable
data class Data(val username: String, val password: String)

fun Application.configureRouting() {
    routing {
        baseRoutes()
        auth()


        post("/show"){
            val msg: Data = call.receive<Data>()
            if(msg.username == "rikudo" && msg.password == "qwerty123"){
                call.respondHtml {

                        head {
                            title { +"Authorized" }
                        }
                        body {
                            style {
                                +"body { font-family: sans-serif; }"
                                +"h1 { color: ${getRandomHexColor()}; font-size: 5em; text-align: center; }"
                            }
                            h1 { +"ВЫ АВТОРИЗОВАНЫ" }
                        }

                }
            }
            else {
                call.respondHtml() {

                        head {
                            title { +"Unauthorized" }
                        }
                        body {
                            style {
                                +"body { font-family: sans-serif; }"
                                +"h1 { color: ${getRandomHexColor()}; font-size: 5em; text-align: center; }"
                            }
                            h1 { +"НЕПРАВИЛЬНЫЙ ЛОГИН И/ИЛИ ПАРОЛЬ" }
                        }

                }
            }



        }




    }




}
