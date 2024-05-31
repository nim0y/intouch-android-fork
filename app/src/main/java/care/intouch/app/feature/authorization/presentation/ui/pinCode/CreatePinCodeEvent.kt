package care.intouch.app.feature.authorization.presentation.ui.pinCode

sealed class CreatePinCodeEvent {
    class Init(val pinCode: String?) : CreatePinCodeEvent()
    class Statement(val pinCode: String) : CreatePinCodeEvent()
}