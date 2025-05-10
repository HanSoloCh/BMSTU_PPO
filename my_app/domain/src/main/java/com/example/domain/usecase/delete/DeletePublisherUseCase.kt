package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.PublisherRepository
import java.util.UUID

class DeletePublisherUseCase(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherId: UUID) {
        publisherRepository.deleteById(publisherId)
    }
}