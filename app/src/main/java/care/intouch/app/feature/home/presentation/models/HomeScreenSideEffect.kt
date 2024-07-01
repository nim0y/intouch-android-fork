package care.intouch.app.feature.home.presentation.models

import care.intouch.uikit.common.StringVO

sealed interface HomeScreenSideEffect {
    data class ShowDialog(
        val title: StringVO,
        val onConfirm: () -> Unit,
        val onDismiss: () -> Unit,
    ) : HomeScreenSideEffect

}
data class DialogState(
    val title: StringVO = StringVO.Plain(""),
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {}
)