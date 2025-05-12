package com.example.domain.usecase.publisher

import com.example.domain.exception.ModelDuplicateException
import com.example.domain.model.PublisherModel
import com.example.domain.repository.PublisherRepository
import com.example.libraryapp.domain.specification.publicher.PublisherIdSpecification

class CreatePublisherUseCase(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherModel: PublisherModel) {
        if (publisherRepository.isContain(PublisherIdSpecification(publisherModel.id)))
            throw ModelDuplicateException("Publisher", publisherModel.id)

        publisherRepository.create(publisherModel)
    }
}