package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.AuthorDao
import com.example.libraryapp.data.mapping.AuthorMapper
import com.example.libraryapp.domain.model.AuthorModel
import com.example.libraryapp.domain.repository.AuthorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class AuthorRepositoryImpl @Inject constructor(
    private val authorDao: AuthorDao
) : AuthorRepository {
    override suspend fun readById(authorId: Uuid): AuthorModel? {
        return authorDao.selectById(authorId)?.let {
            AuthorMapper.toDomain(it)
        }
    }

    override fun readByBookId(bookId: Uuid): Flow<List<AuthorModel>> {
        return authorDao.selectByBookId(bookId).map { entities ->
            entities.map { AuthorMapper.toDomain(it) }
        }
    }

    override suspend fun create(authorModel: AuthorModel) {
        authorDao.insert(AuthorMapper.toData(authorModel))
    }

    override suspend fun update(authorModel: AuthorModel) {
        authorDao.update(AuthorMapper.toData(authorModel))
    }

    override suspend fun deleteById(authorId: Uuid) {
        authorDao.deleteById(authorId)
    }

    override suspend fun addBookToAuthor(authorId: Uuid, bookId: Uuid) {
        authorDao.insertBookToAuthor(authorId, bookId)
    }
}