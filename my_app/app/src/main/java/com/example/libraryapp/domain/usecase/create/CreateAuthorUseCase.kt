package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import com.example.libraryapp.domain.specification.author.AuthorIdSpecification
import java.util.*

class CreateAuthorUseCase(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(authorModel: AuthorModel): UUID {
        if (authorRepository.isContain(AuthorIdSpecification(authorModel.id)))
            throw ModelDuplicateException("Author", authorModel.id)

        return authorRepository.create(authorModel)
    }
}