package care.intouch.app.feature.registration.presentation.viewmodel

import care.intouch.app.feature.registration.presentation.ui.RegistrationScreenState

data class RegistrationState(
    val screenState: RegistrationScreenState = RegistrationScreenState.Loading,
    val userName: String = "",
    val errorPassword: Boolean = false,
    val errorPasswordText: String = "",
    val agreementToTerm: Boolean = true
)
