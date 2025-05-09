package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.specification.bbk.BbkIdSpecification

class UpdateBbkUseCase(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkModel: BbkModel) {
        if (!bbkRepository.isContain(BbkIdSpecification(bbkModel.id)))
            throw ModelNotFoundException("Bbk", bbkModel.id)

        bbkRepository.update(bbkModel)
    }
}