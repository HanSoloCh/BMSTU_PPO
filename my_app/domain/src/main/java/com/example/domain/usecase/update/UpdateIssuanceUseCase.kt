package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.IssuanceRepository
import com.example.libraryapp.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.book.BookIdSpecification
import com.example.libraryapp.domain.specification.issuance.IssuanceIdSpecification
import com.example.libraryapp.domain.specification.user.UserIdSpecification

class UpdateIssuanceUseCase(
    private val issuanceRepository: IssuanceRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(issuanceModel: IssuanceModel) {
        if (!issuanceRepository.isContain(IssuanceIdSpecification(issuanceModel.id)))
            throw ModelNotFoundException("Issuance", issuanceModel.id)

        if (!userRepository.isContain(UserIdSpecification(issuanceModel.userId)))
            throw ModelNotFoundException("User", issuanceModel.userId)

        if (!bookRepository.isContain(BookIdSpecification(issuanceModel.bookId)))
            throw ModelNotFoundException("Book", issuanceModel.bookId)

        issuanceRepository.update(issuanceModel)
    }
}