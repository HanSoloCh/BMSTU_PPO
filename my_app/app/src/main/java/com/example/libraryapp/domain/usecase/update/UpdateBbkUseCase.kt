package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import javax.inject.Inject

class UpdateBbkUseCase @Inject constructor(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkModel: BbkModel) {
        bbkRepository.update(bbkModel)
    }
}