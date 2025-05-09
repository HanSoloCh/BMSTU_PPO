package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.UserRepository
import java.util.*

class DeleteUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: UUID) {
        userRepository.deleteById(userId)
    }
}