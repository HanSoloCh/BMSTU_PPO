package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.BookNoAvailableCopiesException
import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.IssuanceRepository
import com.example.libraryapp.domain.repository.ReservationRepository
import com.example.libraryapp.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.book.BookIdSpecification
import com.example.libraryapp.domain.specification.issuance.IssuanceIdSpecification
import com.example.libraryapp.domain.specification.reservation.ReservationUserIdSpecification
import com.example.libraryapp.domain.specification.user.UserIdSpecification
import kotlinx.coroutines.flow.first
import java.util.*
import javax.inject.Inject

class CreateIssuanceUseCase @Inject constructor(
    private val issuanceRepository: IssuanceRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(issuanceModel: IssuanceModel): UUID {
        if (issuanceRepository.isContain(IssuanceIdSpecification(issuanceModel.id)))
            throw ModelDuplicateException("Issuance", issuanceModel.id)

        if (!userRepository.isContain(UserIdSpecification(issuanceModel.userId)))
            throw ModelNotFoundException("User", issuanceModel.userId)

        val bookModel = getBookOrThrow(issuanceModel.bookId)

        return createIssuance(issuanceModel, bookModel)
    }

    private suspend fun getBookOrThrow(bookId: UUID): BookModel {
        return bookRepository
            .query(BookIdSpecification(bookId))
            .first()
            .firstOrNull()
            ?: throw ModelNotFoundException("Book", bookId)
    }

    private suspend fun createIssuance(issuanceModel: IssuanceModel, book: BookModel): UUID {
        val reservations = reservationRepository
            .query(ReservationUserIdSpecification(issuanceModel.userId))
            .first()

        val reservation = reservations.find { it.bookId == issuanceModel.bookId }

        if (reservation != null) {
            reservationRepository.deleteById(reservation.id)
            return issuanceRepository.create(issuanceModel)
        }

        if (book.availableCopies <= 0) {
            throw BookNoAvailableCopiesException(book.id)
        }

        return issuanceRepository.create(issuanceModel)
    }
}