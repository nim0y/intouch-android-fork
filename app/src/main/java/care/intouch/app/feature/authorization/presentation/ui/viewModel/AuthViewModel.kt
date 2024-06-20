package care.intouch.app.feature.authorization.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateUC
import care.intouch.app.feature.authorization.domain.useCase.LoginByEmailUC
import care.intouch.app.feature.authorization.presentation.ui.models.AuthScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.AuthenticationDataEvent
import care.intouch.app.ui.uiKitSamples.samples.BLANC_STRING
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModule @Inject constructor(
    private val loginByEmailUC: LoginByEmailUC,
    private val getAccountStateUC: GetAccountStateUC,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AuthScreenState(
            password = "",
            login = "",
            loginCaption = StringVO.Plain(""),
            passwordCaption = StringVO.Plain(""),
            isPasswordVisible = false,
            isIconVisible = true,
            isErrorLogin = false,
            isErrorPassword = false
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AuthenticationDataEvent) {
        when (event) {
            is AuthenticationDataEvent.OnLoginButtonClicked -> {
                loginByEmail(event.username, event.password)
            }

            AuthenticationDataEvent.OnLoginErrorChanged -> {
                loginErrorState()
            }

            is AuthenticationDataEvent.OnLoginTextChanged -> {
                saveLogin(event.text)
            }

            AuthenticationDataEvent.OnLoginValidationChecked -> {
                loginValidation()
            }

            AuthenticationDataEvent.OnPasswordErrorChanged -> {
                passwordErrorState()
            }

            AuthenticationDataEvent.OnPasswordIconClick -> {
                onPasswordIconClick()
            }

            is AuthenticationDataEvent.OnPasswordTextChanged -> {
                savePassword(event.text)
            }

            AuthenticationDataEvent.OnPasswordValidationChecked -> {
                passwordValidation()
            }
        }
    }

    private fun savePassword(password: String) {
        _uiState.update { state ->
            state.copy(
                password = password
            )
        }
    }

    private fun onPasswordIconClick() {
        _uiState.update { state ->
            state.copy(
                isPasswordVisible = !uiState.value.isPasswordVisible
            )
        }
    }

    private fun saveLogin(login: String) {
        _uiState.update { state ->
            state.copy(
                login = login
            )
        }
    }

    private fun loginByEmail(username: String, password: String) {
        viewModelScope.launch {
            loginByEmailUC.invoke(username, password)
            getAccountStateUC()
        }
    }

    private fun passwordErrorState() {
        _uiState.update { state ->
            state.copy(
                isErrorPassword = !isValidPassword(uiState.value.password)
            )
        }
    }

    private fun loginErrorState() {
        _uiState.update { state ->
            state.copy(
                isErrorLogin = !isValidEmail(uiState.value.login)
            )
        }
    }

    private fun passwordValidation() {
        if (!isValidPassword(uiState.value.password)) {
            _uiState.update { state ->
                state.copy(
                    passwordCaption = StringVO.Plain("Incorrect. Please try again")
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    passwordCaption = StringVO.Plain(BLANC_STRING)
                )
            }
        }
    }

    private fun loginValidation() {
        if (!isValidEmail(uiState.value.login)) {
            _uiState.update { state ->
                state.copy(
                    loginCaption = StringVO.Plain("Not a valid e-mail address")
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    loginCaption = StringVO.Plain(BLANC_STRING)
                )
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return if (email.isBlank()) {
            true
        } else {
            email.matches(emailRegex.toRegex())
        }
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.isBlank()) return true
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }
}

const val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"