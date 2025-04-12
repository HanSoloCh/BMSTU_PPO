package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.IssuanceDao
import com.example.libraryapp.data.mapping.IssuanceMapper
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.repository.IssuanceRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class IssuanceRepositoryImpl @Inject constructor(
    private val issuanceDao: IssuanceDao
) : IssuanceRepository {
    override suspend fun readById(issuanceId: Uuid): IssuanceModel? {
        return issuanceDao.selectById(issuanceId)?.let {
            IssuanceMapper.toDomain(it)
        }
    }

    override suspend fun create(issuanceModel: IssuanceModel) {
        issuanceDao.insert(IssuanceMapper.toData(issuanceModel))
    }

    override suspend fun update(issuanceModel: IssuanceModel) {
        issuanceDao.update(IssuanceMapper.toData(issuanceModel))
    }

    override suspend fun deleteById(issuanceId: Uuid) {
        issuanceDao.deleteById(issuanceId)
    }

    override fun query(specification: Specification<IssuanceModel>): Flow<List<IssuanceModel>> {
        TODO()
    }
}