package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.AuthorRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
class DeleteAuthorUseCase @Inject constructor(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(authorId : Uuid) {
        authorRepository.deleteById(authorId)
    }
}