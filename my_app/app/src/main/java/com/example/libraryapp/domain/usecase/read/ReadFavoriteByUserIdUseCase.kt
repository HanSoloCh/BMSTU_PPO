package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.UserFavoriteRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ReadFavoriteByUserIdUseCase @Inject constructor(
    private val userFavoriteRepository: UserFavoriteRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(userId: Uuid) = userFavoriteRepository.readByUserId(userId)
}