package com.example.domain.model

import com.example.domain.enums.UserRole
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val name: String,
    val surname: String,
    val secondName: String? = null,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val role: UserRole,
) {
    init {
        require(name.isNotBlank())
        require(surname.isNotBlank())
        require(secondName == null || secondName.isNotBlank())
        require(password.isNotBlank())
        require(isValidEmail(email))
        require(isValidPhone(phoneNumber))
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
        return email.matches(emailRegex)
    }

    private fun isValidPhone(phone: String): Boolean {
        val phoneRegex = "^\\+?[78][0-9]{10}$".toRegex()
        return phone.matches(phoneRegex)
    }
}
