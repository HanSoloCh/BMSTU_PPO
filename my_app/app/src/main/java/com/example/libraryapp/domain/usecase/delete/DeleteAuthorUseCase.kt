package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.AuthorRepository
import java.util.UUID
import javax.inject.Inject

class DeleteAuthorUseCase @Inject constructor(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(authorId: UUID) {
        authorRepository.deleteById(authorId)
    }
}