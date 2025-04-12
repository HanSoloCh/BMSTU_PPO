package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.BbkModel
import java.util.UUID

interface BbkRepository {
    suspend fun readById(bbkId: UUID): BbkModel?

    suspend fun create(bbkModel: BbkModel): UUID

    suspend fun update(bbkModel: BbkModel): Int

    suspend fun deleteById(bbkId: UUID): Int
}