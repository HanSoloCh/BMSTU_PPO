package com.example.libraryapp.domain.specification.reservation

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReservationUserIdSpecification(private val userId: Uuid) : Specification<ReservationModel> {
    override fun specified(candidate: ReservationModel): Boolean =
        candidate.userId == userId
}