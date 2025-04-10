package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.query.issuance.specification.IssuanceUserIdSpecification
import com.example.libraryapp.domain.repository.IssuanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ReadIssuanceByUserIdUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(userId: Uuid) = issuanceRepository.query(IssuanceUserIdSpecification(userId))
}