package com.example.domain.usecase.issuance

import com.example.domain.model.IssuanceModel
import com.example.domain.repository.IssuanceRepository
import com.example.libraryapp.domain.specification.issuance.IssuanceUserIdSpecification
import java.util.*

class ReadIssuanceByUserIdUseCase(
    private val issuanceRepository: IssuanceRepository
) {
    suspend operator fun invoke(userId: UUID): List<IssuanceModel> {
        return issuanceRepository.query(IssuanceUserIdSpecification(userId))
    }
}