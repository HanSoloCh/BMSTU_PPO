package com.example.libraryapp.domain.model

import android.util.Patterns
import java.time.Year
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class PublisherModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val name: String,
    val description: String?,
    val foundationYear: Int?,
    val email: String?,
    val phoneNumber: String?,
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