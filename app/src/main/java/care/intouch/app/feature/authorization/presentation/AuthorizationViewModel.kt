package care.intouch.app.feature.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(

) : ViewModel() {
    private var _state = MutableStateFlow(AuthorizationState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthorizationEvent) {
        when (event) {
            is AuthorizationEvent.OnGetUserInfo -> {
                getUserInfo(event.userId, event.token)
            }

            is AuthorizationEvent.OnSetPassword -> {
                checkPassword(event.password, event.confirmPassword)
            }
        }
    }

    private fun getUserInfo(userId: String?, token: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //TODO get user's info
                _state.update { registrationState ->
                    registrationState.copy(
                        uiState = AuthorizationUiState.SetPassword,
                        userName = "UserName"
                    )
                }
            }
        }
    }

    private fun checkPassword(password: String, confirmPassword: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when {
                    !isPasswordsMatch(password, confirmPassword) -> {
                        _state.update { registrationState ->
                            registrationState.copy(
                                /*uiState = AuthorizationUiState.Error(
                                    errorType = InputPasswordError.NotMatch
                                ),*/
                                error = InputPasswordError.NotMatch
                            )
                        }
                    }
                    else -> {
                        //TODO send password for save
                        _state.update { registrationState ->
                            registrationState.copy(
                                uiState = AuthorizationUiState.Authorized
                            )
                        }
                    }
                }
            }
        }
    }

    private fun isPasswordsMatch(password: String, confirmPassword: String): Boolean = password.equals(confirmPassword)
}