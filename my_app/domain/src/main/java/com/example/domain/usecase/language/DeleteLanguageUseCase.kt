package com.example.domain.usecase.language

import com.example.domain.repository.LanguageRepository
import java.util.*

class DeleteLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(languageId: UUID) {
        languageRepository.deleteById(languageId)
    }
}