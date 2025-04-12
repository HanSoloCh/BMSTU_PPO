package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class CreateFavoriteUseCase @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    suspend operator fun invoke(userId: Uuid, bookId: Uuid) {
        TODO("Add book and user check")
        userFavoriteRepository.create(userId, bookId)
    }
}