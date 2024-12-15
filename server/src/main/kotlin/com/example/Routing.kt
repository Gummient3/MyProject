package com.example

import getRandomHexColor
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.html.*

import com.example.JWT.auth
import com.example.TestRoutes.Test.baseRoutes
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receive
//import io.ktor.server.request.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.serialization.Serializable

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
