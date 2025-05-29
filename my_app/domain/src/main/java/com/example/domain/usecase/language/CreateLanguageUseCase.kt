package com.example.domain.usecase.language

import com.example.domain.exception.ModelDuplicateException
import com.example.domain.model.LanguageModel
import com.example.domain.repository.LanguageRepository
import com.example.domain.specification.language.LanguageIdSpecification
import java.util.*

class CreateLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(languageModel: LanguageModel): UUID {
        if (languageRepository.isContain(LanguageIdSpecification(languageModel.id)))
            throw ModelDuplicateException("Language", languageModel.id)

        return languageRepository.create(languageModel)
    }
}