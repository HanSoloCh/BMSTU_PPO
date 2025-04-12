package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.PublisherModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface PublisherRepository {
    suspend fun readById(publisherId: Uuid): PublisherModel?

    suspend fun create(publisherModel: PublisherModel)

    suspend fun update(publisherModel: PublisherModel)

    suspend fun deleteById(publisherId: Uuid)
}