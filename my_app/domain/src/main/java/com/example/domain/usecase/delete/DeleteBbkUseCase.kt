package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.BbkRepository
import java.util.UUID

class DeleteBbkUseCase(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkId: UUID) {
        bbkRepository.deleteById(bbkId)
    }
}