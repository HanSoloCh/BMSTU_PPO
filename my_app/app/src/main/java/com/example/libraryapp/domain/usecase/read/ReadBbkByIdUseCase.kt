package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import java.util.*

class ReadBbkByIdUseCase(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkId: UUID): BbkModel? {
        return bbkRepository.readById(bbkId)
    }
}
