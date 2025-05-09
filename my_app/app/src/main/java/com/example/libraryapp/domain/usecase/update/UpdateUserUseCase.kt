package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.user.UserIdSpecification

class UpdateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userModel: UserModel) {
        if (!userRepository.isContain(UserIdSpecification(userModel.id)))
            throw ModelNotFoundException("User", userModel.id)

        userRepository.update(userModel)
    }
}