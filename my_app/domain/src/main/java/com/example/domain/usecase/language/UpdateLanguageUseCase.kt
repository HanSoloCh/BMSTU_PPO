package com.example.domain.usecase.language

import com.example.domain.exception.ModelNotFoundException
import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import com.example.domain.specification.language.LanguageIdSpecification

class UpdateLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(languageModel: LanguageModel) {
        if (!languageRepository.isContain(LanguageIdSpecification(languageModel.id)))
            throw ModelNotFoundException("Language", languageModel.id)

        languageRepository.update(languageModel)
    }
}