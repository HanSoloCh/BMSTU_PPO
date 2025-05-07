package com.example.libraryapp.domain.specification.book

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.Specification
import java.util.*

class BookBbkIdSpecification(val bbkId: UUID) : Specification<BookModel> {
    override fun specified(candidate: BookModel): Boolean =
        candidate.bbkId == bbkId
}