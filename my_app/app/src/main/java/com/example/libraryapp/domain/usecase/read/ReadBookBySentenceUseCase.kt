package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.ApuRepository
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.apu.ApuTermSpecification
import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import com.example.libraryapp.domain.specification.book.BookTitleSpecification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ReadBookBySentenceUseCase @Inject constructor(
    private val apuRepository: ApuRepository,
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(sentence: String): Flow<List<BookModel>> {
        val apuList = apuRepository.query(ApuTermSpecification(sentence)).first()
        val spec = if (apuList.isNotEmpty()) {
            BookBbkIdSpecification(apuList.first().bbkId)
        } else {
            BookTitleSpecification(sentence)
        }
        return bookRepository.query(spec)
    }
}