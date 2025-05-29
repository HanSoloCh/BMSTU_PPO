package com.example.domain.repository

import com.example.domain.model.LanguageModel
import com.example.domain.specification.Specification
import java.util.*

interface LanguageRepository {
    suspend fun readById(bbkId: UUID): LanguageModel?

    suspend fun create(bbkModel: LanguageModel): UUID

    suspend fun update(bbkModel: LanguageModel): Int

    suspend fun deleteById(bbkId: UUID): Int

    suspend fun isContain(spec: Specification<LanguageModel>): Boolean

    suspend fun query(spec: Specification<LanguageModel>): List<LanguageModel>
}