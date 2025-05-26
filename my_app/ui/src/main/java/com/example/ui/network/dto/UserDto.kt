package com.example.ui.network.dto

import com.example.domain.domain.util.utils.UserRole
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserDto(
    val id: @Contextual UUID,
    val name: String,
    val surname: String,
    val secondName: String? = null,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val role: UserRole,
)