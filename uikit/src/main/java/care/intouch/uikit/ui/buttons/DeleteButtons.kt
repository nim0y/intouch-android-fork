package care.intouch.uikit.ui.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun DeleteButtonDefault(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    textStyle: TextStyle = InTouchTheme.typography.bodyBold,
    isEnabled: Boolean = true,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        isEnabled = isEnabled,
        enableBackgroundColor = InTouchTheme.colors.errorRed,
        disableBackgroundColor = InTouchTheme.colors.errorRed,
        enableTextColor = InTouchTheme.colors.input,
        disableTextColor = InTouchTheme.colors.input
    )
}

@Composable
fun DeleteButtonActive(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    textStyle: TextStyle = InTouchTheme.typography.bodyBold,
    isEnabled: Boolean = true,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        isEnabled = isEnabled,
        enableBackgroundColor = InTouchTheme.colors.errorMaroonColor,
        disableBackgroundColor = InTouchTheme.colors.errorMaroonColor,
        enableTextColor = InTouchTheme.colors.input,
        disableTextColor = InTouchTheme.colors.input
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteButtonDefaultPreview() {
    InTouchTheme {
        DeleteButtonDefault(
            onClick = {},
            modifier = Modifier,
            text = "Delete Profile Forever",
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteButtonActivePreview() {
    InTouchTheme {
        DeleteButtonActive(
            onClick = {},
            modifier = Modifier,
            text = "Delete Profile Forever",
            isEnabled = true
        )
    }
}