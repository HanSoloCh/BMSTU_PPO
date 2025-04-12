package com.example.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.libraryapp.data.local.AppDataBase
import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.ui.theme.LibraryAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
//    lateinit var appDataBase: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        appDataBase = AppDataBase.getDatabase(this)
//        deleteDatabase("book.db")

        enableEdgeToEdge()
        setContent {
            LibraryAppTheme {
            }
        }
    }
}
