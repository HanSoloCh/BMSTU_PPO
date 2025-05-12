package com.example.domain.usecase.favorite

import com.example.domain.repository.UserFavoriteRepository
import java.util.*

class ReadFavoriteByUserIdUseCase(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    suspend operator fun invoke(userId: UUID): List<UUID> {
        return userFavoriteRepository.readByUserId(userId)
    }
}