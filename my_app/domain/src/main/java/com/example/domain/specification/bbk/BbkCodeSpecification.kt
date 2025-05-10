package com.example.libraryapp.domain.specification.bbk

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.specification.Specification

class BbkCodeSpecification(val code: String) : Specification<BbkModel> {
    override fun specified(candidate: BbkModel): Boolean =
        candidate.code.equals(code, ignoreCase = true)
}