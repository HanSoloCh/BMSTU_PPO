package com.example.ui.screens.book_detail

import com.example.ui.model.BookModel

sealed class BookDetailState {
    object Loading : BookDetailState()
    data class Success(val book: BookModel) : BookDetailState()
    data class Error(val message: String) : BookDetailState()
}