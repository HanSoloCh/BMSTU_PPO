package com.example.libraryapp.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.libraryapp.data.local.dao.ApuDao
import com.example.libraryapp.data.mapping.ApuMapper
import com.example.libraryapp.data.mapping.ReservationMapper
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.query.apu.ApuSpecification
import com.example.libraryapp.domain.query.reservation.ReservationSpecification
import com.example.libraryapp.domain.repository.ApuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ApuRepositoryImpl @Inject constructor(
    private val apuDao: ApuDao
) : ApuRepository {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun readById(apuId: Uuid): ApuModel? {
        return apuDao.selectById(apuId)?.let {
            ApuMapper.toDomain(it)
        }
    }

    override suspend fun create(apuModel: ApuModel) {
        apuDao.insert(ApuMapper.toData(apuModel))
    }

    override suspend fun update(apuModel: ApuModel) {
        apuDao.update(ApuMapper.toData(apuModel))
    }

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun deleteById(apuId: Uuid) {
        apuDao.deleteById(apuId)
    }

    override fun query(specification: ApuSpecification): Flow<List<ApuModel>> {
        val (clause, args) = specification.toSqlClause()
        val sql = "SELECT * FROM apu WHERE $clause"
        return apuDao.select(SimpleSQLiteQuery(sql, args.toTypedArray())).map { entities ->
            entities.map { ApuMapper.toDomain(it) }
        }
    }
}