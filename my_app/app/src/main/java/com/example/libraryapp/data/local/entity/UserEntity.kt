package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object UserEntity : UUIDTable("user") {
    val name = varchar("name", 50)
    val surname = varchar("surname", 50)
    val secondName = varchar("second_name", 50).nullable()
    val email = varchar("email", 50)
    val password = varchar("password", 100)
    val phoneNumber = varchar("phone_number", 20)
    val role = varchar("role", 20)
}
