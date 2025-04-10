package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import javax.inject.Inject

class CreatePublisherUseCase @Inject constructor(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisher: PublisherModel) {
        publisherRepository.create(publisher)
    }
}