package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.IssuanceRepository
import javax.inject.Inject

class CreateIssuanceUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    suspend operator fun invoke(issuanceModel: IssuanceModel) {
        TODO("Add book and user check")
        issuanceRepository.create(issuanceModel)
    }
}