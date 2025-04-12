package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface IssuanceRepository {
    suspend fun readById(issuanceId: Uuid): IssuanceModel?

    suspend fun create(issuanceModel: IssuanceModel)

    suspend fun update(issuanceModel: IssuanceModel)

    suspend fun deleteById(issuanceId: Uuid)

    fun query(specification: Specification<IssuanceModel>): Flow<List<IssuanceModel>>
}