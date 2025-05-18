package com.example.ui.screens.booklist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookListScreen(viewModel: BookListViewModel = hiltViewModel()) {
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
            Column(Modifier.padding(16.dp)) {
                books.forEach { book ->
                    Text(text = book.title, style = MaterialTheme.typography.bodyMedium)
                    if (!book.annotation.isNullOrEmpty()) {
                        Text(text = book.annotation, style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        is BookListState.Error -> {
            Text("Ошибка: ${(state as BookListState.Error).message}", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}
