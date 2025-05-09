package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import java.util.*

class ReadAuthorByIdUseCase(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(authorId: UUID): AuthorModel? {
        return authorRepository.readById(authorId)
    }
}