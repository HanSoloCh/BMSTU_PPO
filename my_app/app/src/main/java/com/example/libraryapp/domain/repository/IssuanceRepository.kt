package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IssuanceRepository {
    suspend fun create(issuanceModel: IssuanceModel) : UUID

    suspend fun update(issuanceModel: IssuanceModel): Int

    suspend fun deleteById(issuanceId: UUID): Int

    suspend fun isContain(spec: Specification<IssuanceModel>): Boolean

    fun query(spec: Specification<IssuanceModel>): Flow<List<IssuanceModel>>
}