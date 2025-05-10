package com.example.libraryapp.domain.usecase.read

import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import java.util.UUID

class ReadPublisherByIdUseCase(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherId: UUID): PublisherModel? {
        return publisherRepository.readById(publisherId)
    }
}

