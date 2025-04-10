package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class CreateFavoriteUseCase @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    suspend operator fun invoke(userId: Uuid, bookId: Uuid) {
        userFavoriteRepository.create(userId, bookId)
    }
}