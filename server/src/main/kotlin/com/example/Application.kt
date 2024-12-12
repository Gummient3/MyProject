package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}


fun Application.module() {
    configureAdministration()
    configureSockets()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title { +"ПРИВЕТ ГНОМ" }
                    style {
                        +"body { margin: 0; font-family: sans-serif; display: flex; justify-content: center; align-items: center; min-height: 100vh; }"
                        +"h1 { font-size: 10vw; text-align: center; }" // 10vw - 10% ширины viewport
                    }
                }
                body {
                    h1 { +"ЛЮБЛЮ ИГОРЕЧКА" }
                }
            }
        }
    }
    }

