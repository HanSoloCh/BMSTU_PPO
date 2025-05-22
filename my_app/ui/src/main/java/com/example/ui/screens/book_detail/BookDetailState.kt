package com.example.ui.screens.book

import com.example.ui.model.BookModel

sealed class BookState {
    object Loading : BookState()
    data class Success(val book: BookModel) : BookState()
    data class Error(val message: String) : BookState()
}