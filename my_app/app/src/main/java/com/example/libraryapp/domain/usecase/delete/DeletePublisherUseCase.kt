package com.example.libraryapp.domain.usecase.delete

import com.example.libraryapp.domain.repository.PublisherRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DeletePublisherUseCase @Inject constructor(
    private val publisherRepository: PublisherRepository
) {
    suspend operator fun invoke(publisherId : Uuid) {
        publisherRepository.deleteById(publisherId)
    }
}