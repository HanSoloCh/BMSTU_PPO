package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.ApuRepository
import java.util.UUID

class DeleteApuUseCase(
    private val apuRepository: ApuRepository
) {
    suspend operator fun invoke(apuInd: UUID) {
        apuRepository.deleteById(apuInd)
    }
}