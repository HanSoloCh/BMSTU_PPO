package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userModel: UserModel) {
        userRepository.update(userModel)
    }
}