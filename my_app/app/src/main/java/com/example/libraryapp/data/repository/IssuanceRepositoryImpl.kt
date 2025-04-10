package com.example.libraryapp.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.dao.IssuanceDao
import com.example.libraryapp.data.mapping.IssuanceMapper
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.model.IssuanceModel
import com.example.libraryapp.domain.query.issuance.IssuanceSpecification
import com.example.libraryapp.domain.repository.IssuanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun query(specification: IssuanceSpecification): Flow<List<IssuanceModel>> {
        val (clause, args) = specification.toSqlClause()
        val sql = "SELECT * FROM issuance WHERE $clause"
        return issuanceDao.select(SimpleSQLiteQuery(sql, args.toTypedArray())).map { entities ->
            entities.map { IssuanceMapper.toDomain(it) }
        }
    }
}