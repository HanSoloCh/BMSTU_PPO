package com.example.ui.navigation

import android.net.Uri
import java.util.*

sealed class Screen(val route: String) {
    object BookList : Screen("bookList")
    object BookDetail : Screen("bookDetail")
    object AuthorBooks : Screen("author/books/{authorId}") {
        fun createRoute(authorId: UUID) = "author/books/$authorId"
    }

    object BbkBooks : Screen("bbk/books/{bbkId}") {
        fun createRoute(bbkId: UUID) = "bbk/books/$bbkId"
    }

    object SearchResults : Screen("searchResults/{query}") {
        fun createRoute(query: String) = "searchResults/${Uri.encode(query)}"
    }

    object UserFavorite : Screen("userFavorite/{userId}") {
        fun createRoute(userId: UUID) = "userFavorite/$userId"
    }

    object AddEntity : Screen("addEntity")

    object Login : Screen("login")
}