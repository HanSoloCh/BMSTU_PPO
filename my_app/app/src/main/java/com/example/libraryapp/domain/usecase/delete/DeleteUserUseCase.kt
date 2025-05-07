package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.UserRepository
import java.util.*
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: UUID) {
        userRepository.deleteById(userId)
    }
}