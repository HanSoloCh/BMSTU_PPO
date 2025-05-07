package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface BbkRepository {
    suspend fun readById(bbkId: UUID): BbkModel?

    suspend fun create(bbkModel: BbkModel): UUID

    suspend fun update(bbkModel: BbkModel): Int

    suspend fun deleteById(bbkId: UUID): Int

    suspend fun isContain(spec: Specification<BbkModel>): Boolean

    fun query(spec: Specification<BbkModel>): Flow<List<BbkModel>>
}