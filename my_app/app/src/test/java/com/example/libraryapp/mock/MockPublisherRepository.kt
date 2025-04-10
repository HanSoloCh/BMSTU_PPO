package com.example.libraryapp.mock

import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class MockPublisherRepository : PublisherRepository {

    val mockPublishers = mutableListOf<PublisherModel>()

    override suspend fun readById(publisherId: Uuid): PublisherModel? {
        return mockPublishers.find { it.id == publisherId }
    }

    override suspend fun create(publisherModel: PublisherModel) {
//        val newPublisher = publisherModel.copy(id = mockPublishers.size + 1)
//        mockPublishers.toMutableList().add(newPublisher)
    }

    override suspend fun update(publisherModel: PublisherModel) {
        val index = mockPublishers.indexOfFirst { it.id == publisherModel.id }
        if (index != -1) {
            mockPublishers[index] = publisherModel
        }
    }

    override suspend fun deleteById(publisherId: Uuid) {
        mockPublishers.toMutableList().removeAll { it.id == publisherId }
    }
}
