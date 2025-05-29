package com.example.ui.screens.book_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.mapping.BookMapper
import com.example.ui.model.BookModel
import com.example.ui.network.BookApi
import com.example.ui.network.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookApi: BookApi,
    private val userApi: UserApi,
    private val mapper: BookMapper
) : ViewModel() {
    private val _state = MutableStateFlow<BookDetailState>(BookDetailState.Loading)
    val state: StateFlow<BookDetailState> = _state

    fun setBook(book: BookModel, userId: UUID?) {
        viewModelScope.launch {
            if (userId == null) {
                _state.value = BookDetailState.Success(book, BookActionsState())
            } else {
                val bookActionsState = BookActionsState(
                    isFavorite = checkIfFavorite(userId, book.id),
                    isReserved = false
                )
                _state.value = BookDetailState.Success(book, bookActionsState)
            }
        }
    }


    fun toggleFavorite(userId: UUID, bookId: UUID) {
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState is BookDetailState.Success) {
                val current = currentState.actionsState
                if (current.isFavorite) removeFromFavoriteBook(userId, bookId) else addToFavoriteBook(userId, bookId)

                _state.value = currentState.copy(
                    actionsState = currentState.actionsState.copy(isFavorite = !current.isFavorite)
                )
            }
        }
    }

    private suspend fun addToFavoriteBook(userId: UUID, bookId: UUID) {
        userApi.addToFavorite(userId, bookId)
    }

    private suspend fun removeFromFavoriteBook(userId: UUID, bookId: UUID) {
        userApi.removeFromFavorite(userId, bookId)
    }

    private suspend fun checkIfFavorite(userId: UUID, bookId: UUID): Boolean {
        val books = userApi.getFavoriteById(userId)
        println(books)
        return books.any { it.id == bookId }
    }
}