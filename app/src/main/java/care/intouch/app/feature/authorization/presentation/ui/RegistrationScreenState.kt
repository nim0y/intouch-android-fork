package care.intouch.app.feature.authorization.presentation.ui

sealed class RegistrationScreenState {
    data object Loading: RegistrationScreenState()
    data object Registration: RegistrationScreenState()
}