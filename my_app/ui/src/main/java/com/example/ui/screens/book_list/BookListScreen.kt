package com.example.ui.screens.book_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ui.item.BookItem

@Composable
fun BookListScreen(
    navController: NavController,
) {
    val viewModel: BookListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    when (state) {
        is BookListState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is BookListState.Success -> {
            val books = (state as BookListState.Success).books
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
            ) {
                itemsIndexed(books) { index, book ->
                    BookItem(book, navController)
                    if (index == books.size - 1) {
                        viewModel.loadBooksPage()
                    }
                }
            }
        }

        is BookListState.Error -> {
            val error = (state as BookListState.Error).message
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ошибка: $error", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
