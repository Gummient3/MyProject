package com.example.JWT

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.User.UserLog
import com.example.User.UserLoginModel
import com.example.secret
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import net.mamoe.yamlkt.Yaml
import java.io.File
import java.util.Date



fun Route.auth(){
    post("/login") {
        println(""" 
            +
            +
            +
            ++
            +
            +
            ++
            +
            +
            +
            +
        """.trimIndent())
        val user = call.receive<UserLog>()

        println(user.username)
        call.respondText("""
            ${user.username}
            ${user.password}
        """.trimIndent())

    }
}