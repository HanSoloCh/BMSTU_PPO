package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ApuRepository {
    suspend fun readById(apuId: UUID?): ApuModel?

    suspend fun create(apuModel: ApuModel): UUID?

    suspend fun update(apuModel: ApuModel): Int

    suspend fun deleteById(apuId: UUID): Int

    fun query(spec: Specification<ApuModel>): Flow<List<ApuModel>>
}