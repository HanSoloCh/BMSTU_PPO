package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.ApuRepository
import java.util.*
import javax.inject.Inject

class DeleteApuUseCase @Inject constructor(
    private val apuRepository: ApuRepository
) {
    suspend operator fun invoke(apuInd: UUID) {
        apuRepository.deleteById(apuInd)
    }
}