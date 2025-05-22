package com.example.ui.screens.booklist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.ui.model.BookModel
import com.example.ui.navigation.Screen

@Composable
fun BookListScreen(
    navController: NavController,
    viewModel: BookListViewModel = hiltViewModel()
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
                    BookCard(book, onClick = {
                        navController.navigate(Screen.Book.createRoute(book.id.toString()))
                    })
                    if (index == books.size - 1) {
                        viewModel.loadBooksPage()
                    }
                }
            }
        }
        is BookListState.Error -> {
            Text("Ошибка: ${(state as BookListState.Error).message}")
            println((state as BookListState.Error).message)
        }
    }
}

@Composable
fun BookCard(book: BookModel, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(book.title, style = MaterialTheme.typography.titleMedium)
            if (!book.annotation.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = book.annotation,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
