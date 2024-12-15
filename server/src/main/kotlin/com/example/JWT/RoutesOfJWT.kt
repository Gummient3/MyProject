package com.example.JWT

//import com.example.User.UserCreds
import com.example.User.UserLog
import com.example.User.getUserByUsername
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
                call.respond(status = HttpStatusCode.NoContent) {

                }
            } else {
                call.respond(status = HttpStatusCode.OK) {
                }
            }
        }catch (e: Exception){
            call.respondText(status = HttpStatusCode.NotFound){
                "No user with that name"
            }
        }

//        val creds: UserCreds = call.receive<UserCreds>()
//        if (creds.password == "Admin123" && creds.username == "Admin"){
//            call.respond(status = HttpStatusCode.OK){
//                "token-blA-B;A-F=-BLA-BLA-"
//            }
//        }
//        else{
//            call.respond(status = HttpStatusCode.Unauthorized) {
//                """"POSHEL NAHUI"
//                ${creds.password}
//                ${creds.username}
//                  """
//            }
//        }

        }
        post("/login") {


            val user = call.receive<UserLog>()

            println(user.username)
            if (user.username == "Admin" && user.password == "Admin123") {
                call.respondText(
                    """
            ${user.username}
            ${user.password}
            ${HttpStatusCode.OK.toString()}
        """.trimIndent()
                )
            } else {
                call.respond("Unauthorized")
            }


        }
    }
