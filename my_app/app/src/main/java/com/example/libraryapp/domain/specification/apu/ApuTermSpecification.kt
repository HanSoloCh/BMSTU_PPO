package com.example.libraryapp.domain.specification.apu

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification


class ApuTermSpecification(private val term: String) : Specification<ApuModel> {
    override fun specified(candidate: ApuModel): Boolean =
        candidate.term.equals(term, ignoreCase = true)
}