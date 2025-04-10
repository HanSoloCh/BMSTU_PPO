package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId : Uuid) {
        userRepository.deleteById(userId)
    }
}