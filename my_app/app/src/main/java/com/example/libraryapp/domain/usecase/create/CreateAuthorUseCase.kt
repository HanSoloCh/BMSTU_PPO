package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import javax.inject.Inject

class CreateAuthorUseCase @Inject constructor(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(author: AuthorModel) {
        authorRepository.create(author)
    }
}