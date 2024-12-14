package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.User.DatabaseConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

import kotlin.random.Random
data class UserDate(var password: String,var username: String ){

}
val secret: String = "1c6782b019f42755f88958970fc82c8558ab1fc2ff67170fa7f45611e6865c8e"
fun main(args: Array<String>) {
    DatabaseConfig.init()
    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)


}


fun Application.module() {
    install(Authentication) {
        jwt("auth-jwt") {
            //realm = myRealm
            verifier(JWT
                .require(Algorithm.HMAC256(secret))
//                .withAudience(audience)
//                .withIssuer(issuer)
                .build())
        }
    }


    configureAdministration()
    configureSockets()
    configureSerialization()
    //configureDatabases()
    configureSecurity()
    configureRouting()

}


