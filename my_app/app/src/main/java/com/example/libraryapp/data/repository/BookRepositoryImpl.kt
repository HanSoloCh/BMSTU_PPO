package com.example.libraryapp.data.repository

import com.example.libraryapp.data.local.dao.BookDao
import com.example.libraryapp.data.mapping.BookMapper
import com.example.libraryapp.domain.model.BookModel
import com.example.libraryapp.domain.repository.BookRepository
import com.example.libraryapp.domain.specification.Specification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun readById(bookId: Uuid): BookModel? {
        return bookDao.selectById(bookId)?.let {
            BookMapper.toDomain(it)
        }
    }

    override fun readByAuthorId(authorId: Uuid): Flow<List<BookModel>> {
        return bookDao.selectByAuthorId(authorId).map { entities ->
            entities.map { BookMapper.toDomain(it) }
        }
    }

    override fun query(specification: Specification<BookModel>): Flow<List<BookModel>> {
        TODO()
    }


    override suspend fun create(bookModel: BookModel): Int {
        return bookDao.insert(BookMapper.toData(bookModel)).toInt()
    }

    override suspend fun update(bookModel: BookModel) {
        bookDao.update(BookMapper.toData(bookModel))
    }

    override suspend fun deleteById(bookId: Uuid) {
        bookDao.deleteById(bookId)
    }
}