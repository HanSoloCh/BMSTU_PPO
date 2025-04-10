package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.query.issuance.IssuanceSpecification
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface IssuanceRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun readById(issuanceId: Uuid): IssuanceModel?

    suspend fun create(issuanceModel: IssuanceModel)

    suspend fun update(issuanceModel: IssuanceModel)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun deleteById(issuanceId: Uuid)

    fun query(specification: IssuanceSpecification): Flow<List<IssuanceModel>>
}