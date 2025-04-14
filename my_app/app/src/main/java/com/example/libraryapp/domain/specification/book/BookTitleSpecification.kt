package com.example.libraryapp.domain.specification.book

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.Specification

class BookTitleSpecification(val title: String) : Specification<BookModel> {
    override fun specified(candidate: BookModel): Boolean =
        candidate.title.equals(title, ignoreCase = true)
}