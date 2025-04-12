package com.example.libraryapp.domain.specification

import com.example.libraryapp.domain.model.ApuModel

interface Specification<T> {
    fun specified(candidate: T): Boolean
}