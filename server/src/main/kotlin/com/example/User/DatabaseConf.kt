package com.example.User

import  org.jetbrains.exposed.sql.Database

object DatabaseConfig {
    fun init() {
        println("бд создана")
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/mydatabase", // URL базы данных
            driver = "org.postgresql.Driver", // Драйвер для PostgreSQL
            user = "myuser", // Имя пользователя
            password = "mypassword" // Пароль
        )
    }
}