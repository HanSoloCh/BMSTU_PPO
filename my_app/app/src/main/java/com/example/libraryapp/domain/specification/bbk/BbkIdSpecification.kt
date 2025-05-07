package com.example.libraryapp.domain.specification.bbk

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.specification.Specification
import java.util.*

class BbkIdSpecification(val id: UUID) : Specification<BbkModel> {
    override fun specified(candidate: BbkModel): Boolean = candidate.id == id
}