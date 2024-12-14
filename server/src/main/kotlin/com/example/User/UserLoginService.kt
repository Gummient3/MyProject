package com.example.User

import org.jetbrains.exposed.sql.transactions.transaction

class UserLoginService {
    object UserLoginService {
        // Создание пользователя
        fun createUser(username: String, passwordHash: String): UserLogin {
            return transaction {
                UserLogin.new {
                    this.username = username
                    this.passwordHash = passwordHash
                }
            }
        }
    }
}