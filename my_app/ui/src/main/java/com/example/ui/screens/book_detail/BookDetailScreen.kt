package com.example.ui.screens.book_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ui.common.enums.UserRole
import com.example.ui.common.json.appJson
import com.example.ui.model.AuthorModel
import com.example.ui.model.BbkModel
import com.example.ui.model.BookModel
import com.example.ui.navigation.Screen
import com.example.ui.util.UserStore

@Composable
fun BookDetailScreen(
    navController: NavController,
) {
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val bookJson = savedStateHandle?.get<String>("book")
    val book = bookJson?.let {
        appJson.decodeFromString(BookModel.serializer(), it)
    }

    val viewModel: BookDetailViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(book) {
        if (book != null) {
            viewModel.setBook(book, UserStore.getId())
        }
    }

    when (state) {
        is BookDetailState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is BookDetailState.Success -> {
            val book = (state as BookDetailState.Success).book
            val actions = (state as BookDetailState.Success).actionsState
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Основная информация о книге
                Text(book.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))

                // Авторы

                if (book.authors.isNotEmpty()) {
                    Text("Авторы:", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    book.authors.forEach { author ->
                        Text(
                            text = author.name,
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.clickable {
                                val authorJson = appJson.encodeToString(AuthorModel.serializer(), author)
                                navController.currentBackStackEntry?.savedStateHandle?.set("author", authorJson)
                                navController.navigate(Screen.AuthorBooks.createRoute(author.id))
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Аннотация
                Text(
                    text = book.annotation ?: "Аннотация отсутствует",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Детальная информация
                Text("Детальная информация:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                book.publisherModel?.let { publisher ->
                    DetailRow("Издательство:", publisher.name)
                }

                book.publicationYear?.let { year ->
                    DetailRow("Год издания:", year.toString())
                }

                // ISBN
                book.codeISBN?.let { isbn ->
                    DetailRow("ISBN:", isbn)
                }

                // ББК
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        text = "ББК",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.width(150.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = book.bbkModel.code,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            val bbkJson = appJson.encodeToString(BbkModel.serializer(), book.bbkModel)
                            navController.currentBackStackEntry?.savedStateHandle?.set("bbk", bbkJson)
                            navController.navigate(Screen.BbkBooks.createRoute(book.bbkModel.id))
                        }
                    )
                }

                // Тип носителя
                book.mediaType?.let { type ->
                    DetailRow("Тип носителя:", type)
                }

                // Объем
                book.volume?.let { volume ->
                    DetailRow("Объем:", volume)
                }

                // Языки
                DetailRow("Язык:", book.language ?: "не указан")
                book.originalLanguage?.let { lang ->
                    DetailRow("Оригинальный язык:", lang)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    if (UserStore.getRole() == UserRole.READER) {
                        Button(
                            onClick = {
                                viewModel.toggleFavorite(UserStore.getId()!!, book.id)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (actions.isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(if (actions.isFavorite) "Убрать из избранного" else "В избранное")
                        }
                        Button(onClick = {

                        }) {
                            Text("Заказать")
                        }
                    }
                }
            }
        }

        is BookDetailState.Error -> {
            val error = (state as BookDetailState.Error).message
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ошибка: $error", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

// Вспомогательный компонент для отображения строки деталей
@Composable
private fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(150.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}