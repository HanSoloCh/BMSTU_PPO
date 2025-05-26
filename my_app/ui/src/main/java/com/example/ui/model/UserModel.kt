package com.example.ui.model

import com.example.domain.domain.util.utils.UserRole
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserModel(
    val id: @Contextual UUID,
    val name: String,
    val surname: String,
    val secondName: String?,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val role: UserRole,
)
