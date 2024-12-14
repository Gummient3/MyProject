package com.example.JWT

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.User.UserCreds
import com.example.User.UserLog
import com.example.User.UserLoginModel
import com.example.secret
import getRandomHexColor
import io.ktor.http.HttpStatusCode
import io.ktor.server.html.respondHtml
import io.ktor.server.http.httpDateFormat
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.style
import net.mamoe.yamlkt.Yaml
import java.io.File
import java.util.Date



fun Route.auth(){
    post("/registration"){

        val creds: UserCreds = call.receive<UserCreds>()
        if (creds.password == "Admin123" && creds.username == "Admin"){
            call.respondText(status = HttpStatusCode.OK){
                "token-blA-B;A-F=-BLA-BLA-"
            }
        }
        else{
            call.respondText(status = HttpStatusCode.Unauthorized) {
                """"POSHEL NAHUI"
                ${creds.password}
                ${creds.username} 
                  """
            }
        }

    }
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
        if (user.username == "Admin" && user.password == "Admin123"){
            call.respondText("""
            ${user.username}
            ${user.password}
            ${HttpStatusCode.OK.toString()}
        """.trimIndent())
        }
        else{
            call.respond("Unauthorized")
        }


    }
}