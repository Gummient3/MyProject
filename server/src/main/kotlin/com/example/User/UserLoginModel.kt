package com.example.User

import com.example.UserService.Users
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

data class UserLog(val username: String,val password: String){

}

object UserLoginModel : IntIdTable() {
    val username = varchar("username", 50).uniqueIndex() // Уникальный столбец для username
    val passwordHash = varchar("password_hash", 255) // Хэш пароля
}

// DAO-класс пользователя
class UserLogin(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, UserLogin>(UserLoginModel)

    var username by UserLoginModel.username
    var passwordHash by UserLoginModel.passwordHash
}
