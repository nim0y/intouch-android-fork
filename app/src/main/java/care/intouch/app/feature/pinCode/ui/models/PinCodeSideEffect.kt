package care.intouch.app.feature.pinCode.ui.models

sealed interface PinCodeSideEffect {
    data object Skip : PinCodeSideEffect
    data object Save : PinCodeSideEffect
    data object ResetPinCode : PinCodeSideEffect
}