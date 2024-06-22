package care.intouch.app.feature.pinCode.ui.models

sealed class PinCodeEvent {
    data class OnPinCodeChanged(val pinCode: String) : PinCodeEvent()
    data object OnSkipButtonClicked : PinCodeEvent()
    data object OnSaveButtonClicked : PinCodeEvent()
    data object OnBackButtonClicked : PinCodeEvent()
}