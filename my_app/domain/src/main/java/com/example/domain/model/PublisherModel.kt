package com.example.libraryapp.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Year
import java.util.*

@Serializable
data class PublisherModel(
    val id: @Contextual UUID = UUID.randomUUID(),
    val name: String,
    val description: String? = null,
    val foundationYear: Int? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
) {
    init {
        require(name.isNotBlank())
        require(description == null || description.isNotBlank())
        require(foundationYear in 0..Year.now().value)
        require(email == null || isValidEmail(email))
        require(phoneNumber == null || isValidPhone(phoneNumber))
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