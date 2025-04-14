package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.repository.IssuanceRepository
import java.util.UUID
import javax.inject.Inject

class ReadIssuanceByUserIdUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    operator fun invoke(userId: UUID) = issuanceRepository.readByUserId(userId)
}