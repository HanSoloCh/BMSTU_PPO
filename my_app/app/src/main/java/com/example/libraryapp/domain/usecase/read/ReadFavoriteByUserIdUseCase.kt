package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import java.util.UUID
import javax.inject.Inject

class ReadFavoriteByUserIdUseCase @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    operator fun invoke(userId: UUID) = userFavoriteRepository.readByUserId(userId)
}