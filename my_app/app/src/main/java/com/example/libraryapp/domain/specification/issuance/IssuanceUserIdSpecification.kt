package com.example.libraryapp.domain.specification.issuance

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.specification.Specification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class IssuanceUserIdSpecification(private val userId: Uuid) : Specification<IssuanceModel> {
    override fun specified(candidate: IssuanceModel): Boolean =
        candidate.userId == userId
}