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

import kotlin.random.Random

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
    configureRouting()

    }

