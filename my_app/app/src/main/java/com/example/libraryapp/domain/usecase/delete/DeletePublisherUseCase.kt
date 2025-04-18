package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.PublisherRepository
import java.util.UUID
import javax.inject.Inject

class DeletePublisherUseCase @Inject constructor(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherId: UUID) {
        publisherRepository.deleteById(publisherId)
    }
}