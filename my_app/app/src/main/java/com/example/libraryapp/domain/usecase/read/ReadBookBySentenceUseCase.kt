package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.query.apu.specification.ApuTermSpecification
import com.example.libraryapp.domain.query.book.specification.BookBbkIdSpecification
import com.example.libraryapp.domain.query.book.specification.BookTitleSpecification
import com.example.libraryapp.domain.repository.ApuRepository
import com.example.libraryapp.domain.repository.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class ReadBookBySentenceUseCase @Inject constructor(
    private val apuRepository: ApuRepository,
    private val bookRepository: BookRepository
) {
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