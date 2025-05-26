package com.example.ui.screens.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.mapping.BookMapper
import com.example.ui.model.BookModel
import com.example.ui.network.BookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookApi: BookApi,
    private val mapper: BookMapper
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
                val newBooks = bookApi.getBooks(page = currentPage, pageSize = pageSize).map {
                    mapper.toUi(it)
                }
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