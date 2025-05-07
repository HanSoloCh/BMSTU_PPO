package com.example.libraryapp.domain.usecase.update

import com.example.libraryapp.domain.exception.ModelNotFoundException
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import com.example.libraryapp.domain.specification.publicher.PublisherIdSpecification
import javax.inject.Inject

class UpdatePublisherUseCase @Inject constructor(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherModel: PublisherModel) {
        if (!publisherRepository.isContain(PublisherIdSpecification(publisherModel.id)))
            throw ModelNotFoundException("Publisher", publisherModel.id)

        publisherRepository.update(publisherModel)
    }
}