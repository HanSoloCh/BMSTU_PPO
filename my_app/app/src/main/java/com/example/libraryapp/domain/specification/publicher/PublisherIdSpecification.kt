package com.example.libraryapp.domain.specification.publicher

import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.specification.Specification
import java.util.*

class PublisherIdSpecification(val id: UUID) : Specification<PublisherModel> {
    override fun specified(candidate: PublisherModel): Boolean =
        candidate.id == id
}