package com.example.ui.screens.book

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.model.BookModel
import java.util.UUID

@Composable
fun BookScreen(
    book: BookModel,
) {
    val viewModel: BookViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBook(bookId)
    }

    when (state) {
        is BookState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is BookState.Success -> {
            val book = (state as BookState.Success).book
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(book.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(book.annotation ?: "Нет аннотации", style = MaterialTheme.typography.bodyLarge)
            }
        }
        is BookState.Error -> {
            val error = (state as BookState.Error).message
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ошибка: $error", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}