package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.BookNoAvailableCopiesException
import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.ReservationRepository
import com.example.libraryapp.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.book.BookIdSpecification
import com.example.libraryapp.domain.specification.reservation.ReservationIdSpecification
import com.example.libraryapp.domain.specification.user.UserIdSpecification
import kotlinx.coroutines.flow.first
import java.util.*

/**
 * Create reservation must decrement available copies
 */
class CreateReservationUseCase(
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(reservationModel: ReservationModel): UUID {
        if (reservationRepository.isContain(ReservationIdSpecification(reservationModel.id)))
            throw ModelDuplicateException("Reservation", reservationModel.id)

        if (!userRepository.isContain(UserIdSpecification(reservationModel.userId)))
            throw ModelNotFoundException("User", reservationModel.userId)

        if (!bookRepository.isContain(BookIdSpecification(reservationModel.bookId)))
            throw ModelNotFoundException("Book", reservationModel.bookId)

        if (bookRepository.query(BookIdSpecification(reservationModel.bookId)).first()
                .first().availableCopies <= 0
        )
            throw BookNoAvailableCopiesException(reservationModel.bookId)

        return reservationRepository.create(reservationModel)
    }
}