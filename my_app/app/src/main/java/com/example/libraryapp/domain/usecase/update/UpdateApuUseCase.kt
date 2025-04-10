package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.repository.ApuRepository
import javax.inject.Inject

class UpdateApuUseCase @Inject constructor(
    private val apuRepository: ApuRepository
) {
    suspend operator fun invoke(apuModel: ApuModel) {
        apuRepository.update(apuModel)
    }
}