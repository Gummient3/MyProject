package com.example.DatabaseConf

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction



val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
val user = "postgres"  // Имя пользователя (можно заменить)
val password = "jkemCuAg13"  // Пароль (замените на свой)


// Функция для подключения к базе данных
fun connectToDatabase() {

    // Подключение к базе данных
    Database.connect(url = dbUrl, driver = "org.postgresql.Driver", user = user, password = password)
    println("подключение к дб выполнено")
}

fun checkConnectDB(){
    try {
        // Подключение к базе данных
        Database.connect(dbUrl, driver = "org.postgresql.Driver", user = user, password = password)

        // Выполнение простого запроса для проверки подключения
        transaction {
            val result = exec("SELECT 1") // Простой запрос для проверки подключения
            if (result != null) {
                println("Подключение успешно!")
            } else {
                println("Ошибка при выполнении запроса.")
            }
        }
    } catch (e: Exception) {
        println("Ошибка подключения: ${e.message}")
    }
}

