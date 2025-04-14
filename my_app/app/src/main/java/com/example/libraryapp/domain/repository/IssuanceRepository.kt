package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.IssuanceModel
import java.util.UUID

interface IssuanceRepository {
    suspend fun create(issuanceModel: IssuanceModel)

    suspend fun update(issuanceModel: IssuanceModel): Int

    suspend fun deleteById(issuanceId: UUID): Int

//    fun query(specification: Specification<IssuanceModel>): Flow<List<IssuanceModel>>
}