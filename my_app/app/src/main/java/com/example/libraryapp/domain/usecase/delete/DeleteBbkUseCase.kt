package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.BbkRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteBbkUseCase @Inject constructor(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkId: Uuid) {
        bbkRepository.deleteById(bbkId)
    }
}