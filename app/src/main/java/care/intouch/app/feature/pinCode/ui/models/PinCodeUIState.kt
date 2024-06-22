package care.intouch.app.feature.pinCode.ui.models

import care.intouch.uikit.common.StringVO

data class PinCodeUIState(
    val title: StringVO = StringVO.Plain(""),
    val isBackButtonVisible: Boolean = true,
    val isBackButtonEnabled: Boolean = true,
    val description: StringVO = StringVO.Plain(""),
    val isDescriptionVisible: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: StringVO = StringVO.Plain(""),
    val isSaveButtonEnabled: Boolean = false,
    val isSkipButtonEnabled: Boolean = true
)