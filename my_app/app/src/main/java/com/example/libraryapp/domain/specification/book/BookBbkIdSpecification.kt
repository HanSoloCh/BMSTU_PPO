package com.example.libraryapp.domain.specification.book

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.Specification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class BookBbkIdSpecification(private val bbkId: Uuid) : Specification<BookModel> {
    override fun specified(candidate: BookModel): Boolean =
        candidate.bbkId == bbkId
}