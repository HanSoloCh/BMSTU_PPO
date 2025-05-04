package com.example.libraryapp.data.specification

import com.example.libraryapp.data.entity.BookTable
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.specification.book.BookBbkIdSpecification
import com.example.libraryapp.domain.specification.book.BookPublisherIdSpecification
import com.example.libraryapp.domain.specification.book.BookTitleSpecification
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like

object BookSpecToExpressionMapper {
    fun map(spec: Specification<BookModel>): Op<Boolean> = when (spec) {
        is BookBbkIdSpecification -> BookTable.bbkId eq spec.bbkId
        is BookPublisherIdSpecification -> BookTable.publisherId eq spec.publisherId
        is BookTitleSpecification -> BookTable.title like spec.title

        else -> throw IllegalArgumentException("Unknown spec")
    }
}