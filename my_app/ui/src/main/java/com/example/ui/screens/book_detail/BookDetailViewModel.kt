package com.example.ui.screens.book_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.mapping.BookMapper
import com.example.ui.model.BookModel
import com.example.ui.network.BookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookApi: BookApi,
    private val mapper: BookMapper
) : ViewModel() {
    private val _state = MutableStateFlow<BookDetailState>(BookDetailState.Loading)
    val state: StateFlow<BookDetailState> = _state

    fun setBook(book: BookModel) {
        _state.value = BookDetailState.Success(book)
    }

    fun loadBook(bookId: UUID) {
        viewModelScope.launch {
            _state.value = BookDetailState.Loading
            try {
                val book = mapper.toUi(bookApi.getBook(bookId))
                _state.value = BookDetailState.Success(book)
            } catch (e: Exception) {
                _state.value = BookDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
}