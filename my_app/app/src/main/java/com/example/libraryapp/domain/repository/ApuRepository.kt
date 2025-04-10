package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.query.apu.ApuSpecification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface ApuRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun readById(apuId: Uuid): ApuModel?

    suspend fun create(apuModel: ApuModel)

    suspend fun update(apuModel: ApuModel)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun deleteById(apuId: Uuid)

    fun query(specification: ApuSpecification): Flow<List<ApuModel>>
}