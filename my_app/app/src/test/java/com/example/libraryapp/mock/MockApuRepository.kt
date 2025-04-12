package com.example.libraryapp.mock

import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.Specification
import com.example.libraryapp.domain.repository.ApuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class MockApuRepository : ApuRepository {

    val apus = mutableListOf<ApuModel>()

    override suspend fun readById(apuId: Uuid): ApuModel? {
        return apus.find { it.id == apuId }
    }

    override suspend fun create(apuModel: ApuModel) {
        apus.add(apuModel)
    }

    override suspend fun update(apuModel: ApuModel) {
        val index = apus.indexOfFirst { it.id == apuModel.id }
        if (index != -1) {
            apus[index] = apuModel
        }
    }

    override suspend fun deleteById(apuId: Uuid) {
        apus.removeAll { it.id == apuId }
    }

    override fun query(specification: Specification<ApuModel>): Flow<List<ApuModel>> {
        TODO()
    }
}
