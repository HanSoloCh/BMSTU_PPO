package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.domain.model.ApuModel
import com.example.libraryapp.domain.repository.ApuRepository
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.specification.apu.ApuIdSpecification
import com.example.libraryapp.domain.specification.bbk.BbkIdSpecification

class UpdateApuUseCase(
    private val apuRepository: ApuRepository,
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(apuModel: ApuModel) {
        if (!apuRepository.isContain(ApuIdSpecification(apuModel.id)))
            throw ModelNotFoundException("Apu", apuModel.id)

        if (!bbkRepository.isContain(BbkIdSpecification(apuModel.bbkId)))
            throw ModelNotFoundException("Bbk", apuModel.bbkId)

        apuRepository.update(apuModel)
    }
}