package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.mapping.AuthorMapper
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID
import javax.inject.Inject


class AuthorRepositoryImpl @Inject constructor() : AuthorRepository {
    override suspend fun readById(authorId: UUID): AuthorModel? = withContext(Dispatchers.IO) {
        transaction {
            AuthorEntity.select { AuthorEntity.id eq authorId }.firstOrNull()?.let {
                AuthorMapper.toDomain(it)
            }
        }
    }

    override suspend fun create(authorModel: AuthorModel) = withContext(Dispatchers.IO) {
        transaction {
            AuthorEntity.insert {
                AuthorMapper.toInsertStatement(authorModel, it)
            }
                .resultedValues?.first()?.let { AuthorMapper.toDomain(it).id }
                ?: throw NoSuchElementException("Error saving author: $authorModel")
        }
    }

    override suspend fun update(authorModel: AuthorModel) = withContext(Dispatchers.IO) {
        transaction {
            AuthorEntity.update({ AuthorEntity.id eq authorModel.id }) {
                AuthorMapper.toUpdateStatement(authorModel, it)
            }
        }
    }

    override suspend fun deleteById(authorId: UUID) = withContext(Dispatchers.IO) {
        transaction {
            AuthorEntity.deleteWhere { AuthorEntity.id eq authorId }
        }
    }

//    override fun query(specification: Specification<ApuModel>): Flow<List<ApuModel>> {
//        TODO("Not yet implemented")
//    }
}