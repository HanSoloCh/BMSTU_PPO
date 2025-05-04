package com.example.libraryapp.domain.specification.issuance

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.specification.Specification
import java.util.UUID

class IssuanceUserIdSpecification(val userId: UUID) : Specification<IssuanceModel> {
    override fun specified(candidate: IssuanceModel): Boolean =
        candidate.userId == userId
}