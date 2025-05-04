package com.example.libraryapp.domain.specification.reservation

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import java.util.UUID

class ReservationUserIdSpecification(val userId: UUID) : Specification<ReservationModel> {
    override fun specified(candidate: ReservationModel): Boolean =
        candidate.userId == userId
}