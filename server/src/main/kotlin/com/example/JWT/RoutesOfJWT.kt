package com.example.JWT

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.secret
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import net.mamoe.yamlkt.Yaml
import org.h2.engine.User
import java.io.File
import java.util.Date



fun Route.auth(){
    post("/login") {
        val user = call.receive<User>()

    }
}