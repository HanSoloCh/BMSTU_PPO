package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import java.util.*

class ReadLanguageByIdUseCase(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(languageId: UUID): LanguageModel? {
        return languageRepository.readById(languageId)
    }
}
