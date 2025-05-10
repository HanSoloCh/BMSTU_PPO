package com.example.libraryapp.domain.specification.issuance

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.specification.Specification
import java.util.UUID

class IssuanceIdSpecification(val id: UUID) : Specification<IssuanceModel> {
    override fun specified(candidate: IssuanceModel): Boolean =
        candidate.id == id
}