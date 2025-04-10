package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import javax.inject.Inject

class UpdateAuthorUseCase @Inject constructor(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(authorModel: AuthorModel) {
        authorRepository.update(authorModel)
    }
}