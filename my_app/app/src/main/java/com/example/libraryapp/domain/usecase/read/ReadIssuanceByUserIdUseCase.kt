package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.specification.issuance.IssuanceUserIdSpecification
import com.example.libraryapp.domain.repository.IssuanceRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ReadIssuanceByUserIdUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(userId: Uuid) = issuanceRepository.query(IssuanceUserIdSpecification(userId))
}