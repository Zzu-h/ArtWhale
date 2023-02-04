package com.capstone.artwhale.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.usecase.auth.GetTokenInfoUseCase
import com.capstone.artwhale.domain.usecase.auth.LoginUseCase
import com.capstone.artwhale.presentation.common.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val getTokenInfoUseCase: GetTokenInfoUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<NetworkState>(InitialState)
    val loginState: StateFlow<NetworkState> = _loginState

    init {
        autoLogin()
    }

    fun login(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.emit(Loading)
            loginUseCase(email)
                .onSuccess { _loginState.emit(Success) }
                .onFailure { _loginState.emit(Error(it.message)) }
        }
    }

    private fun autoLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferencesManager.getJwt()?.let {
                _loginState.emit(Loading)
                getTokenInfoUseCase()
                    .onSuccess { _loginState.emit(Success) }
                    .onFailure { _loginState.emit(InitialState) }
            }
        }
    }
}