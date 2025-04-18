package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.repository.ApuRepository
import javax.inject.Inject

class CreateApuUseCase @Inject constructor(
    private val apuRepository: ApuRepository
) {
    suspend operator fun invoke(apuModel: ApuModel) {
        apuRepository.create(apuModel)
    }
}