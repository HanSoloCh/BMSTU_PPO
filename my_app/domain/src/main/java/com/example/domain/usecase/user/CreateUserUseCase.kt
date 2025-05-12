package com.example.domain.usecase.user

import com.example.domain.exception.ModelDuplicateException
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.user.UserIdSpecification

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userModel: UserModel) {
        if (userRepository.isContain(UserIdSpecification(userModel.id)))
            throw ModelDuplicateException("User", userModel.id)

        userRepository.create(userModel)
    }
}