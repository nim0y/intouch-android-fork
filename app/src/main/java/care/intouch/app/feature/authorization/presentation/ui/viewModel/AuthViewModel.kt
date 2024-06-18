package care.intouch.app.feature.authorization.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateUC
import care.intouch.app.feature.authorization.domain.useCase.LoginByEmailUC
import care.intouch.app.feature.authorization.presentation.ui.models.AuthScreenState
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
            isPasswordVisible = false,
            isIconVisible = true,
            isErrorLogin = false,
            isErrorPassword = false
        )
    )
    val uiState = _uiState.asStateFlow()

    fun savePassword(password: String) {
        _uiState.update { state ->
            state.copy(
                password = password
            )
        }
    }

    fun onPasswordIconClick() {
        _uiState.update { state ->
            state.copy(
                isPasswordVisible = !uiState.value.isPasswordVisible
            )
        }
    }

    fun saveLogin(login: String) {
        _uiState.update { state ->
            state.copy(
                login = login
            )
        }
    }

    fun loginByEmail(username: String, password: String) {
        viewModelScope.launch {
            loginByEmailUC.invoke(username, password)
            getAccountStateUC()
        }
    }

    fun passwordErrorState(): Boolean {
        return uiState.value.isErrorPassword == isValidPassword(uiState.value.password)
    }

    fun loginErrorState(): Boolean {
        return uiState.value.isErrorLogin == isValidEmail(uiState.value.login)
    }

    fun passwordValidation(): StringVO {
        if (uiState.value.isErrorPassword == isValidPassword(uiState.value.password)) {
            return StringVO.Plain("Incorrect. Please try again")
        } else {
            return StringVO.Plain(BLANC_STRING)
        }
    }

    fun loginValidation(): StringVO {
        if (uiState.value.isErrorLogin == isValidEmail(uiState.value.login)) {
            return StringVO.Plain("Not a valid e-mail address")
        } else {
            return StringVO.Plain(BLANC_STRING)
        }
    }

    fun isValidEmail(email: String): Boolean {
        return if (email.isBlank()) {
            true
        } else {
            email.matches(emailRegex.toRegex())
        }
    }

    fun isValidPassword(password: String): Boolean {
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