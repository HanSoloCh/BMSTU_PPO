package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import java.util.*
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    val userFavoriteRepository: UserFavoriteRepository
) {
    suspend fun invoke(userId: UUID, bookId: UUID) {
        userFavoriteRepository.delete(userId, bookId)
    }
}