package care.intouch.app.feature.registration.presentation.ui

sealed class RegistrationScreenState {
    data object Loading: RegistrationScreenState()
    data object Registration: RegistrationScreenState()
}