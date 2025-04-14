package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import java.util.UUID
import javax.inject.Inject

class CreateFavoriteUseCase @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    suspend operator fun invoke(userId: UUID, bookId: UUID) {
        TODO("Add book and user check")
        userFavoriteRepository.create(userId, bookId)
    }
}