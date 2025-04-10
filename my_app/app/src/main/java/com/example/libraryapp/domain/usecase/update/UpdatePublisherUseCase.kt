package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import javax.inject.Inject

class UpdatePublisherUseCase @Inject constructor(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherModel: PublisherModel) {
        publisherRepository.update(publisherModel)
    }
}