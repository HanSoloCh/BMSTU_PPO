package com.example.libraryapp.domain.specification.author

import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.specification.Specification
import java.util.*

class AuthorIdSpecification(val id: UUID) : Specification<AuthorModel> {
    override fun specified(candidate: AuthorModel): Boolean = candidate.id == id
}