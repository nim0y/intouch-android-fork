package care.intouch.uikit.ui.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun TertiaryButton(
    onClick: () -> Unit,
    modifier: Modifier,
    icon: Int = R.drawable.icon_arrow_small_right,
    isEnabled: Boolean = true,
    enableBackgroundColor: Color = InTouchTheme.colors.mainGreen,
    disableBackgroundColor: Color = InTouchTheme.colors.unableElementLight,
    enableTextColor: Color = InTouchTheme.colors.input,
    disableTextColor: Color = InTouchTheme.colors.input,
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    shape: Shape = RoundedCornerShape(12.dp),
    text: String,
    textStyle: TextStyle = InTouchTheme.typography.bodySemibold,
) {
    Button(
        shape = shape,
        modifier = modifier,
        contentPadding = contentPadding,
        enabled = isEnabled,
        colors = ButtonColors(
            containerColor = enableBackgroundColor,
            contentColor = enableTextColor,
            disabledContainerColor = disableBackgroundColor,
            disabledContentColor = disableTextColor,
        ),
        onClick = { onClick.invoke() }
    )
    {
        Text(text = text, style = textStyle)
        Box(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = InTouchTheme.colors.input
        )
    }
}

@Composable
fun TertiaryButtonActive(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    isEnabled: Boolean = true,
) {
    TertiaryButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        isEnabled = isEnabled,
        enableBackgroundColor = InTouchTheme.colors.darkGreen,
        disableBackgroundColor = InTouchTheme.colors.darkGreen,
        enableTextColor = InTouchTheme.colors.input,
        disableTextColor = InTouchTheme.colors.input,
    )
}

@Composable
fun TertiaryButtonDefault(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    isEnabled: Boolean = true,
) {
    TertiaryButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        isEnabled = isEnabled,
        enableBackgroundColor = InTouchTheme.colors.mainGreen,
        disableBackgroundColor = InTouchTheme.colors.mainGreen,
        enableTextColor = InTouchTheme.colors.input,
        disableTextColor = InTouchTheme.colors.input,
    )
}

@Preview(showBackground = true)
@Composable
fun TertiaryButtonDefault() {
    InTouchTheme {
        TertiaryButtonDefault(
            onClick = {},
            modifier = Modifier,
            text = "Security"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TertiaryButtonActive() {
    InTouchTheme {
        TertiaryButtonActive(
            onClick = {},
            modifier = Modifier,
            text = "Security"
        )
    }
}
