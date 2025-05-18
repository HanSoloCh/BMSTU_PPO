package com.example.ui.screens.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.example.ui.network.BookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookApi: BookApi,
) : ViewModel() {
    private val _state = MutableStateFlow<BookListState>(BookListState.Loading)
    val state: StateFlow<BookListState> = _state

    init {
        loadBooks()
    }
    private fun loadBooks() {
        viewModelScope.launch {
            try {
                val books = bookApi.getAllBooks()
                _state.value = BookListState.Success(books)
            } catch (e: Exception) {
                _state.value = BookListState.Error(e.message ?: "Unknown error")
            }
        }
    }
}