package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.IssuanceRepository
import com.example.libraryapp.domain.specification.issuance.IssuanceUserIdSpecification
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class ReadIssuanceByUserIdUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    operator fun invoke(userId: UUID): Flow<List<IssuanceModel>> {
        return issuanceRepository.query(IssuanceUserIdSpecification(userId))
    }
}