package care.intouch.app.feature.pinCode.ui.models

data class PinCodeScreenState(
    val isInitial: Boolean = true,
    val currentPinCode: String = "",
    val tempPinCode: String = "",
    val isValid: Boolean = false,
    val isError: Boolean = false,
)