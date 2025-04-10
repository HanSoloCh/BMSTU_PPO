package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BbkModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface BbkRepository {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun readById(bbkId: Uuid): BbkModel?

    suspend fun create(bbkModel: BbkModel)

    suspend fun update(bbkModel: BbkModel)

    @OptIn(ExperimentalUuidApi::class)
    suspend fun deleteById(bbkId: Uuid)
}