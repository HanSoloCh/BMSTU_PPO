package com.example.libraryapp.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.libraryapp.data.local.dao.BookDao
import com.example.libraryapp.data.local.dao.ReservationDao
import com.example.libraryapp.data.mapping.ReservationMapper
import com.example.libraryapp.domain.model.ReservationModel
import com.example.libraryapp.domain.query.reservation.ReservationSpecification
import com.example.libraryapp.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReservationRepositoryImpl @Inject constructor(
    private val reservationDao: ReservationDao
) : ReservationRepository {
    override suspend fun readById(reservationId: Uuid): ReservationModel? {
        return reservationDao.selectById(reservationId)?.let {
            ReservationMapper.toDomain(it)
        }
    }

    override suspend fun create(reservationModel: ReservationModel) {
        reservationDao.createReservation(ReservationMapper.toData(reservationModel))
    }

    override suspend fun update(reservationModel: ReservationModel) {
        reservationDao.update(ReservationMapper.toData(reservationModel))
    }

    override suspend fun deleteById(reservationId: Uuid) {
        reservationDao.deleteReservation(reservationId)
    }

    override fun query(specification: ReservationSpecification): Flow<List<ReservationModel>> {
        val (clause, args) = specification.toSqlClause()
        val sql = "SELECT * FROM reservation WHERE $clause"
        return reservationDao.select(SimpleSQLiteQuery(sql, args.toTypedArray())).map { entities ->
            entities.map { ReservationMapper.toDomain(it) }
        }
    }
}