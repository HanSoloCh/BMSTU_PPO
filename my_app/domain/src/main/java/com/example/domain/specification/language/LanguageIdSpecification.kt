package com.example.domain.specification.language

import com.example.domain.model.LanguageModel
import com.example.domain.specification.Specification
import java.util.*

class LanguageIdSpecification(val id: UUID) : Specification<LanguageModel> {
    override fun specified(candidate: LanguageModel): Boolean = candidate.id == id
}