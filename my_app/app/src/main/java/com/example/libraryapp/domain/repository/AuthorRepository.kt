package com.example.libraryapp.domain.repository

import com.example.libraryapp.domain.model.AuthorModel
import java.util.UUID

interface AuthorRepository {
    suspend fun readById(authorId: UUID): AuthorModel?

    suspend fun create(authorModel: AuthorModel): UUID

    suspend fun update(authorModel: AuthorModel): Int

    suspend fun deleteById(authorId: UUID): Int

    suspend fun isContain(authorId: UUID): Boolean
}