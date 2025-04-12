package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ApuRepository {
    suspend fun readById(apuId: Uuid): ApuModel?

    suspend fun create(apuModel: ApuModel)

    suspend fun update(apuModel: ApuModel)

    suspend fun deleteById(apuId: Uuid)

    fun query(specification: Specification<ApuModel>): Flow<List<ApuModel>>
}