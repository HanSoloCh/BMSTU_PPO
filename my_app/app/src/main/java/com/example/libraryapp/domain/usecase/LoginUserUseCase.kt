package com.example.libraryapp.domain.usecase

import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository

class LoginUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): UserModel? {
        if (email.isBlank() || password.isBlank()) {
            return null
        }

        return userRepository.login(email, hashPassword(password))
    }

    private fun hashPassword(password: String): String {
        return password
    }
}