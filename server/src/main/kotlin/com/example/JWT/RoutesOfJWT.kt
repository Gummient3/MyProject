package com.example.JWT


//import com.example.DB

//import com.example.User.getUserByUsername
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import kotlinx.serialization.Serializable

@Serializable
data class UserCreds(val username: String, val password: String){



}
fun Route.auth() {
    post("/registration") {
        val creds: UserCreds = call.receive<UserCreds>()
        println(creds.username)
        println(creds.password)
//        println(getUserByUsername(creds.username))
        try {
            if (creds.username == "Suren" && creds.password == "krasav4ik") {
                call.respond(status = HttpStatusCode.OK) {

                }
            } else {
                call.respond(status = HttpStatusCode.Unauthorized) {
                }
            }
        }catch (e: Exception){
            call.respondText(status = HttpStatusCode.InternalServerError){
                "No user with that name"
            }
        }


        }
        post("/login") {
//
//
//            val user = call.receive<UserLog>()
//
//            println(user.username)
//            if (user.username == "Admin" && user.password == "Admin123") {
//                call.respondText(
//                    """
//            ${user.username}
//            ${user.password}
//            ${HttpStatusCode.OK.toString()}
//        """.trimIndent()
//                )
//            } else {
//                call.respond("Unauthorized")
//            }


        }
    }
