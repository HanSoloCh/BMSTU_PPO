package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.specification.bbk.BbkIdSpecification
import java.util.*
import javax.inject.Inject

class CreateBbkUseCase @Inject constructor(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkModel: BbkModel): UUID {
        if (bbkRepository.isContain(BbkIdSpecification(bbkModel.id)))
            throw ModelDuplicateException("Bbk", bbkModel.id)

        return bbkRepository.create(bbkModel)
    }
}