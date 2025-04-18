package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.IssuanceRepository
import javax.inject.Inject

class UpdateIssuanceUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository
) {
    suspend operator fun invoke(issuanceModel: IssuanceModel) {
        issuanceRepository.update(issuanceModel)
    }
}