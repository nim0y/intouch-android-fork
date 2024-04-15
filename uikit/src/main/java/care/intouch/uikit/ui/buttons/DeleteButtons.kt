package care.intouch.uikit.ui.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun DeleteButtonDefault(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    textStyle: TextStyle = InTouchTheme.typography.bodyBold,
    isEnabled: Boolean = true,
    isActive: Boolean = false,
) {
    IntouchButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        textStyle = textStyle,
        isEnabled = isEnabled,
        isActive = isActive,
        contentPadding = PaddingValues(horizontal = 51.5.dp, vertical = 11.5.dp),
        enableBackgroundColor = InTouchTheme.colors.errorMaroonColor,
        disableBackgroundColor = InTouchTheme.colors.errorRed,
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
            isActive = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteButtonActivePreview() {
    InTouchTheme {
        DeleteButtonDefault(
            onClick = {},
            modifier = Modifier,
            text = "Delete Profile Forever",
            isActive = true
        )
    }
}