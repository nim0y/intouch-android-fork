package care.intouch.app.feature.profile.presentation.ui.security

sealed class SecurityUiState {
    data object SetPassword: SecurityUiState()
    data object DeleteProfile: SecurityUiState()
    data object ProfileDeleted: SecurityUiState()
}
