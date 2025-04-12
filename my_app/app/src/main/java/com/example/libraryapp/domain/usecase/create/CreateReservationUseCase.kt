package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.repository.ReservationRepository
import com.example.libraryapp.domain.util.TransactionRunner
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

/**
 * Create reservation, decrement available copies
 */
class CreateReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
    private val bookRepository: BookRepository,
    private val transactionRunner: TransactionRunner
) {
    @OptIn(ExperimentalUuidApi::class)
    suspend operator fun invoke(reservationModel: ReservationModel) {
        transactionRunner.runInTransaction {
            val bookModel = bookRepository.readById(reservationModel.bookId)
                ?: throw NoSuchElementException("Book with id ${reservationModel.bookId} not found")
            bookRepository.update(bookModel.copy(availableCopies = bookModel.availableCopies - 1))
            reservationRepository.create(reservationModel)
        }
    }
}