package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.repository.UserRepository
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