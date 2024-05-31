package care.intouch.app.feature.authorization.presentation.ui.pinCode

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CreatePinCodeScreenState {

    @Immutable
    data object Default : CreatePinCodeScreenState

    @Immutable
    data object Confirmed : CreatePinCodeScreenState

    @Immutable
    data object NotConfirmed : CreatePinCodeScreenState

    @Immutable
    data object Error : CreatePinCodeScreenState
    companion object {
        val Initial: CreatePinCodeScreenState = Default
    }
}