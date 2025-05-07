package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.ReservationRepository
import com.example.libraryapp.domain.repository.UserRepository
import com.example.libraryapp.domain.specification.book.BookIdSpecification
import com.example.libraryapp.domain.specification.reservation.ReservationIdSpecification
import com.example.libraryapp.domain.specification.user.UserIdSpecification
import javax.inject.Inject

class UpdateReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(reservationModel: ReservationModel) {
        if (!reservationRepository.isContain(ReservationIdSpecification(reservationModel.id)))
            throw ModelNotFoundException("Reservation", reservationModel.id)

        if (!userRepository.isContain(UserIdSpecification(reservationModel.userId)))
            throw ModelNotFoundException("User", reservationModel.userId)

        if (!bookRepository.isContain(BookIdSpecification(reservationModel.bookId)))
            throw ModelNotFoundException("Book", reservationModel.bookId)

        reservationRepository.update(reservationModel)
    }
}