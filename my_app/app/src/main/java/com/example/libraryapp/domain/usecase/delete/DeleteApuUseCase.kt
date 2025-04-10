package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.ApuRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeleteApuUseCase @Inject constructor(
    private val apuRepository: ApuRepository
) {
    suspend operator fun invoke(apuInd: Uuid) {
        apuRepository.deleteById(apuInd)
    }
}