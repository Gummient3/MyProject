package com.example.User

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll


// Модель таблицы Users
object Users : Table() {
    val id = integer("id").autoIncrement().autoIncrement() // Первичный ключ
    val username = varchar("username", 255).uniqueIndex() // Уникальное имя пользователя
    val passwordHash = varchar("password_hash", 255) // Хеш пароля

    // Устанавливаем id как первичный ключ
    override val primaryKey = PrimaryKey(id, name = "PK_User_Id") // Устанавливаем primaryKey на столбец id
}

// Функция для добавления пользователя в базу данных
fun insertUser(username: String, passwordHash: String) {
    transaction {
        Users.insert {
            it[this.username] = username
            it[this.passwordHash] = passwordHash
        }
    }
}

// Функция для получения пользователя по имени
fun getUserByUsername(username: String): String? {
    return transaction {
        // Новый способ использования select с условиями
        val query = Users.selectAll().where(Users.username eq username)
        query.singleOrNull()?.get(Users.username)
    }
}
