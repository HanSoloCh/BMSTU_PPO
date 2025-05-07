package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.IssuanceRepository
import java.util.*
import javax.inject.Inject

class DeleteIssuanceUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    suspend operator fun invoke(issuanceId: UUID) {
        issuanceRepository.deleteById(issuanceId)
    }
}