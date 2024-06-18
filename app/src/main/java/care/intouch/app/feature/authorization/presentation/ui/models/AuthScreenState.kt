package care.intouch.app.feature.authorization.presentation.ui.models

data class AuthScreenState(
    val password: String = "",
    val login: String = "",
    val isPasswordVisible: Boolean = false,
    val isIconVisible: Boolean = true,
    val isErrorLogin: Boolean = false,
    val isErrorPassword: Boolean = false,
)