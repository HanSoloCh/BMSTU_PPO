package com.example.domain.usecase.language

import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import com.example.domain.specification.language.LanguageNameSpecification

class ReadLanguageByNameUseCase(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(languageCode: String): LanguageModel? {
        return languageRepository.query(LanguageNameSpecification(languageCode)).firstOrNull()
    }
}
