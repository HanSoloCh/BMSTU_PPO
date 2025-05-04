package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ReadFavoriteByUserIdUseCase @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    operator fun invoke(userId: UUID): Flow<List<UUID>> {
        return userFavoriteRepository.readByUserId(userId)
    }
}