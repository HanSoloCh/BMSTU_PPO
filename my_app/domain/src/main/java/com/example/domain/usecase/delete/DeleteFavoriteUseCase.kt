package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import java.util.UUID

class DeleteFavoriteUseCase(
    val userFavoriteRepository: UserFavoriteRepository
) {
    suspend fun invoke(userId: UUID, bookId: UUID) {
        userFavoriteRepository.delete(userId, bookId)
    }
}