package com.example.libraryapp.domain.specification

interface Specification<T> {
    fun specified(candidate: T): Boolean
}