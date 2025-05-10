package com.example.libraryapp.domain.specification.apu

import com.example.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import java.util.UUID

class ApuIdSpecification(val id: UUID) : Specification<ApuModel> {
    override fun specified(candidate: ApuModel): Boolean = candidate.id == id
}