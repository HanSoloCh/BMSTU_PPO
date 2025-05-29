package com.example.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.mapping.UserMapper
import com.example.ui.network.AuthApi
import com.example.ui.network.dto.LoginRequest
import com.example.ui.util.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val userMapper: UserMapper
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
                println(authApi.login(LoginRequest(_state.value.email, _state.value.password)))
                val user = UserMapper().toUi(authApi.login(LoginRequest(_state.value.email, _state.value.password)))
                UserStore.save(user)
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