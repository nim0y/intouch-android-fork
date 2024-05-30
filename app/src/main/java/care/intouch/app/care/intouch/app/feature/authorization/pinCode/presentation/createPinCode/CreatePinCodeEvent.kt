package care.intouch.app.care.intouch.app.feature.authorization.pinCode.presentation.createPinCode

sealed class CreatePinCodeEvent {
    class Create(val pinCode: String): CreatePinCodeEvent()
    class Confirm(val pinCode: String): CreatePinCodeEvent()
}