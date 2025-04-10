package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteFavoriteUseCase @Inject constructor(
    val userFavoriteRepository: UserFavoriteRepository
) {
    suspend fun invoke(userId: Uuid, bookId: Uuid) {
        userFavoriteRepository.delete(userId, bookId)
    }
}