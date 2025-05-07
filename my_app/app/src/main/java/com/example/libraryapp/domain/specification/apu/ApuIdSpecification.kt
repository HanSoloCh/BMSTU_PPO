package com.example.libraryapp.domain.specification.apu

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import java.util.*

class ApuIdSpecification(val id: UUID) : Specification<ApuModel> {
    override fun specified(candidate: ApuModel): Boolean = candidate.id == id
}