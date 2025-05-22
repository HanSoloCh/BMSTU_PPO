package com.example.ui.screens.booklist

import com.example.ui.model.BookModel


sealed class BookListState {
    object Loading : BookListState()
    data class Success(val books: List<BookModel>) : BookListState()
    data class Error(val message: String) : BookListState()
}