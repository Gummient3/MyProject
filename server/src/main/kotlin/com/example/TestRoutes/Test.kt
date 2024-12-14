package com.example.TestRoutes

import getRandomHexColor
import io.ktor.http.HttpStatusCode
import io.ktor.server.html.respondHtml
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.style
import kotlinx.html.title
import kotlin.random.Random

object Test {
    fun Route.baseRoutes(){
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