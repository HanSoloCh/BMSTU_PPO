package com.example.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.mapping.BookMapper
import com.example.ui.model.BookModel
import com.example.ui.network.AuthApi
import com.example.ui.network.BookApi
import com.example.ui.network.LoginRequest
import com.example.ui.screens.book_list.BookListState
import com.example.ui.util.TokenStore
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun login(onComplete: () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val token = authApi.login(LoginRequest(_state.value.email, _state.value.password))
                TokenStore.saveToken(token)
                println("HUI $token")
                _state.update { it.copy(isLoading = false) }
                onComplete()
            } catch (e: ClientRequestException) {
                val message = try {
                    e.response.bodyAsText()
                } catch (_: Exception) {
                    "Ошибка авторизации"
                }
                _state.update { it.copy(isLoading = false, error = message) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.toString()) }
            }
        }
    }
}