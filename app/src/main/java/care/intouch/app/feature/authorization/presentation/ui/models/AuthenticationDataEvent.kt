package care.intouch.app.feature.authorization.presentation.ui.models

sealed class AuthenticationDataEvent {
    data class OnPasswordTextChanged(val text: String) : AuthenticationDataEvent()
    data class OnLoginTextChanged(val text: String) : AuthenticationDataEvent()
    data class OnLoginButtonClicked(val username: String, val password: String) :
        AuthenticationDataEvent()

    data object OnLoginValidationChecked : AuthenticationDataEvent()
    data object OnPasswordValidationChecked : AuthenticationDataEvent()
    data object OnPasswordErrorChanged : AuthenticationDataEvent()
    data object OnLoginErrorChanged : AuthenticationDataEvent()
    data object OnPasswordIconClick : AuthenticationDataEvent()
}