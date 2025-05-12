package com.example.domain.usecase.favorite

import com.example.domain.repository.UserFavoriteRepository
import java.util.*

class DeleteFavoriteUseCase(
    val userFavoriteRepository: UserFavoriteRepository
) {
    suspend fun invoke(userId: UUID, bookId: UUID) {
        userFavoriteRepository.delete(userId, bookId)
    }
}