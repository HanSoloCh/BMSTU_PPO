package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.specification.author.AuthorIdSpecification
import javax.inject.Inject

class UpdateAuthorUseCase @Inject constructor(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(authorModel: AuthorModel) {
        if (!authorRepository.isContain(AuthorIdSpecification(authorModel.id)))
            throw ModelNotFoundException("Author", authorModel.id)

        authorRepository.update(authorModel)
    }
}