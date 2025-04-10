package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.PublisherDao
import com.example.libraryapp.data.mapping.PublisherMapper
import com.example.libraryapp.domain.model.PublisherModel
import com.example.libraryapp.domain.repository.PublisherRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class PublisherRepositoryImpl @Inject constructor(
    private val publisherDao: PublisherDao
) : PublisherRepository {
    override suspend fun readById(publisherId: Uuid): PublisherModel? {
        return publisherDao.selectById(publisherId)?.let {
            PublisherMapper.toDomain(it)
        }
    }

    override suspend fun create(publisherModel: PublisherModel) {
        publisherDao.insert(PublisherMapper.toData(publisherModel))
    }

    override suspend fun update(publisherModel: PublisherModel) {
        publisherDao.update(PublisherMapper.toData(publisherModel))
    }

    override suspend fun deleteById(publisherId: Uuid) {
        publisherDao.deleteById(publisherId)
    }
}