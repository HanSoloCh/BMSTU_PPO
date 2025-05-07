package com.example.libraryapp.domain.specification.reservation

import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.specification.Specification
import java.util.*

class ReservationIdSpecification(val id: UUID) : Specification<ReservationModel> {
    override fun specified(candidate: ReservationModel): Boolean =
        candidate.id == id
}