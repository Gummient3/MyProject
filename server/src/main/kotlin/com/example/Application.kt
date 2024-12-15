package com.example

//import com.example.User.DatabaseConfig
import com.example.DatabaseConf.checkConnectDB
import com.example.DatabaseConf.connectToDatabase
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

data class UserDate(var password: String,var username: String ){

}
val secret: String = "1c6782b019f42755f88958970fc82c8558ab1fc2ff67170fa7f45611e6865c8e"
fun main(args: Array<String>) {

    EngineMain.main(args)
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)


}


fun Application.module() {


    connectToDatabase()
    checkConnectDB()
    configureAdministration()
    configureSockets()
    configureSerialization()
    //configureDatabases()
    configureSecurity()
    configureRouting()

}


