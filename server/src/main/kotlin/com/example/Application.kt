package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
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

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}
fun createToken(userId: String): String {
    val algorithm = Algorithm.HMAC256("your_secret_key") // ключ для подписи
    return JWT.create()
        .withClaim("user_id", userId)  // добавляем данные пользователя
        .sign(algorithm)
}

fun Application.module() {
    install(Authentication) {
        jwt("jwt-auth") {
            realm = "Access to protected routes"
            verifier(
                JWT.require(Algorithm.HMAC256("f91079d07520fbbf82e9feb55fcaf89a719924a70bef5e3b07e2533204aa971e"))
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("user_id").asString() != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }


    }
    configureAdministration()
    configureSockets()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()

}


