package com.example.shopforgroceries.auth.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopforgroceries.auth.events.AuthEvent
import com.example.shopforgroceries.auth.state.AuthState
import com.example.shopforgroceries.data.remote.request.LoginRequest
import com.example.shopforgroceries.domain.usecases.auth.AuthenticationUseCases
import com.example.shopforgroceries.general.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

    private val authenticationUseCases: AuthenticationUseCases
    ) : ViewModel() {
        private val _state = mutableStateOf(
            AuthState(
                isAuthenticated = false
            )
        )
        val state: State<AuthState> = _state

        private val _eventFlow = MutableSharedFlow<AuthEvent>()
        val eventFlow = _eventFlow.asSharedFlow()

        fun createEvent(event: AuthEvent){
            onEvent(event)
        }
        private fun onEvent(event: AuthEvent){
            when(event){
                is AuthEvent.Login ->{
                    val request = LoginRequest(
                        email = event.email,
                        password = event.password
                    )
                }
                else -> {}
            }
        }
        private fun login(request: LoginRequest){
            authenticationUseCases.login(request = request)
                .onEach{
                    when(it){
                        is Resource.Loading->{
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                        is Resource.Error->{
                            _state.value = state.value.copy(
                                isLoading = false,
                                errorOccurred = true,
                                errorMessage = it.message ?:"An unexpected error occurred"
                            )
                        }
                        is Resource.Success->{
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                            _eventFlow.emit(
                                AuthEvent.LoginSuccess
                            )
                        }

                    }
                }
                .launchIn(viewModelScope)
        }
    }