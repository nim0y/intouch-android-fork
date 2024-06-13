package care.intouch.app.feature.authorization.pinCode.ui

import androidx.compose.runtime.Immutable

@Immutable
sealed interface PinCodeEnterState {
    @Immutable
    data object Default : PinCodeEnterState

    @Immutable
    data object Confirmed : PinCodeEnterState

    @Immutable
    data object NotConfirmed : PinCodeEnterState

    companion object {
        val Initial: PinCodeEnterState = Default
    }
}