package care.intouch.app.care.intouch.app.feature.authorization.pinCode.presentation.createPinCode

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CreatePinCodeScreenState {

    @Immutable
    data object Create : CreatePinCodeScreenState

    @Immutable
    data object Confirm : CreatePinCodeScreenState

    @Immutable
    data object ConfirmSuccess : CreatePinCodeScreenState

    @Immutable
    data object Error : CreatePinCodeScreenState

//    @Immutable
//    data class Confirm(val pinCode: String) : CreatePinCodeScreenState
//
//    @Immutable
//    data object NotConfirmed : CreatePinCodeScreenState
//
//    @Immutable
//    data class Error(val error: kotlin.Error) : CreatePinCodeScreenState

    companion object {
        val Initial: CreatePinCodeScreenState = Create
    }
}