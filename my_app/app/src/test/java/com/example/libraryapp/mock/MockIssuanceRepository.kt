package com.example.libraryapp.mock

import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.query.issuance.IssuanceSpecification
import com.example.libraryapp.domain.repository.IssuanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class MockIssuanceRepository : IssuanceRepository {
    private val mockIssuances = mutableListOf<IssuanceModel>()

    override suspend fun readById(issuanceId: Uuid): IssuanceModel? {
        return mockIssuances.find { it.id == issuanceId }
    }

    override suspend fun create(issuanceModel: IssuanceModel) {
        // Симулируем добавление записи
//        val newIssuance = issuanceModel.copy(id = mockIssuances.size + 1)
//        mockIssuances.toMutableList().add(newIssuance)
    }

    override suspend fun update(issuanceModel: IssuanceModel) {
        val index = mockIssuances.indexOfFirst { it.id == issuanceModel.id }
        if (index != -1) {
            mockIssuances[index] = issuanceModel
        }
    }

    override suspend fun deleteById(issuanceId: Uuid) {
        mockIssuances.toMutableList().removeAll { it.id == issuanceId }
    }

    override fun query(specification: IssuanceSpecification): Flow<List<IssuanceModel>> {
        return flow { emit(mockIssuances.filter { true }) }
    }
}
