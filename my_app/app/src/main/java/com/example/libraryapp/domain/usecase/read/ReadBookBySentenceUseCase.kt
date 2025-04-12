package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.apu.ApuTermSpecification
import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import com.example.libraryapp.domain.specification.book.BookTitleSpecification
import com.example.libraryapp.domain.repository.ApuRepository
import com.example.libraryapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

class ReadBookBySentenceUseCase @Inject constructor(
    private val apuRepository: ApuRepository,
    private val bookRepository: BookRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    operator fun invoke(string: String): Flow<List<BookModel>> {
        return apuRepository.query(ApuTermSpecification(string)).flatMapLatest { apuList ->
            if (apuList.isNotEmpty()) {
                val spec = BookBbkIdSpecification(apuList.first().bbkId)
                bookRepository.query(spec)
            } else {
                val spec = BookTitleSpecification(string)
                bookRepository.query(spec)
            }
        }
    }
}