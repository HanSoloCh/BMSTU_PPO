package com.example.domain.specification.language

import com.example.domain.model.LanguageModel
import com.example.domain.specification.Specification

class LanguageNameSpecification(val name: String) : Specification<LanguageModel> {
    override fun specified(candidate: LanguageModel): Boolean =
        candidate.name.equals(name, ignoreCase = true)
}