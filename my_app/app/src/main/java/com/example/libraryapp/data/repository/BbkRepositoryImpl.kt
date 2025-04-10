package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.BbkDao
import com.example.libraryapp.data.mapping.BbkMapper
import com.example.libraryapp.domain.model.BbkModel
import com.example.libraryapp.domain.repository.BbkRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class BbkRepositoryImpl @Inject constructor(
    private val bbkDao: BbkDao
) : BbkRepository {
    override suspend fun readById(bbkId: Uuid): BbkModel? {
        return bbkDao.selectById(bbkId)?.let {
            BbkMapper.toDomain(it)
        }
    }

    override suspend fun create(bbkModel: BbkModel) {
        bbkDao.insert(BbkMapper.toData(bbkModel))
    }

    override suspend fun update(bbkModel: BbkModel) {
        bbkDao.update(BbkMapper.toData(bbkModel))
    }

    override suspend fun deleteById(bbkId: Uuid) {
        bbkDao.deleteById(bbkId)
    }
}