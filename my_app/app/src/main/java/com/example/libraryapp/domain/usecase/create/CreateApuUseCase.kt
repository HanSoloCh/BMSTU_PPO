package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.repository.ApuRepository
import com.example.libraryapp.domain.repository.BbkRepository
import com.example.libraryapp.domain.specification.apu.ApuIdSpecification
import com.example.libraryapp.domain.specification.bbk.BbkIdSpecification
import java.util.*

class CreateApuUseCase(
    private val apuRepository: ApuRepository,
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(apuModel: ApuModel): UUID {
        if (apuRepository.isContain(ApuIdSpecification(apuModel.id)))
            throw ModelDuplicateException("Apu", apuModel.id)

        if (!bbkRepository.isContain(BbkIdSpecification(apuModel.bbkId)))
            throw ModelNotFoundException("Bbk", apuModel.bbkId)

        return apuRepository.create(apuModel)
    }
}