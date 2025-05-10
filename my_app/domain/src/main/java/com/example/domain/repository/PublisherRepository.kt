package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PublisherRepository {
    suspend fun readById(publisherId: UUID): PublisherModel?

    suspend fun create(publisherModel: PublisherModel): UUID

    suspend fun update(publisherModel: PublisherModel): Int

    suspend fun deleteById(publisherId: UUID): Int

    suspend fun isContain(spec: Specification<PublisherModel>): Boolean

    fun query(spec: Specification<PublisherModel>): Flow<List<PublisherModel>>
}