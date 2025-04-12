package com.example.libraryapp.domain.specification.book

import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.Specification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class BookPublisherIdSpecification(private val publisherId: Uuid) : Specification<BookModel> {
    override fun specified(candidate: BookModel): Boolean =
        candidate.publisherId == publisherId
}