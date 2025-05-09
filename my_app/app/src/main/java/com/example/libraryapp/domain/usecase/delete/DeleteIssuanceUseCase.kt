package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.IssuanceRepository
import java.util.*

class DeleteIssuanceUseCase(
    private val issuanceRepository: IssuanceRepository
) {
    suspend operator fun invoke(issuanceId: UUID) {
        issuanceRepository.deleteById(issuanceId)
    }
}