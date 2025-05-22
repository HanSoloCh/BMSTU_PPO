package com.example.ui.screens.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.model.BookModel
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

    private var currentPage = 1
    private val pageSize = 20
    private var isLoading = false
    private var isLastPage = false

    private val books = mutableListOf<BookModel>()

    init {
        loadBooksPage()
    }
    fun loadBooksPage() {
        if (isLoading || isLastPage) return

        isLoading = true
        viewModelScope.launch {
            try {
                val newBooks = bookApi.getBooks(page = currentPage, pageSize = pageSize)
                if (newBooks.isEmpty()) {
                    isLastPage = true
                } else {
                    books.addAll(newBooks)
                    _state.value = BookListState.Success(books.toList())
                    currentPage++
                }
            } catch (e: Exception) {
                _state.value = BookListState.Error(e.message ?: "Unknown error")
            } finally {
                isLoading = false
            }
        }
    }
}