package com.example.ui.screens.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.network.BookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookApi: BookApi
) : ViewModel() {
    private val _state = MutableStateFlow<BookState>(BookState.Loading)
    val state: StateFlow<BookState> = _state

    fun loadBook(bookId: UUID) {
        viewModelScope.launch {
            _state.value = BookState.Loading
            try {
                val book = bookApi.getBook(bookId)
                _state.value = BookState.Success(book)
            } catch (e: Exception) {
                _state.value = BookState.Error(e.message ?: "Unknown error")
            }
        }
    }
}