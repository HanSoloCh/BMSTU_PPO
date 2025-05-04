package com.example.libraryapp.domain.usecase.create

import com.example.libraryapp.domain.exception.ModelDuplicateException
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import com.example.libraryapp.domain.specification.publicher.PublisherIdSpecification
import javax.inject.Inject

class CreatePublisherUseCase @Inject constructor(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherModel: PublisherModel) {
        if (publisherRepository.isContain(PublisherIdSpecification(publisherModel.id)))
            throw ModelDuplicateException("Publisher", publisherModel.id)

        publisherRepository.create(publisherModel)
    }
}