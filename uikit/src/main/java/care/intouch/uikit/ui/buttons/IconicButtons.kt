package care.intouch.uikit.ui.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme


@Composable
fun IntouchIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    icon: Int = R.drawable.icon_feather_in_circle,
    isEnabled: Boolean = true,
    isHasStroke: Boolean = false,
    enableBackgroundColor: Color = InTouchTheme.colors.mainGreen,
    disableBackgroundColor: Color = InTouchTheme.colors.unableElementLight,
    enableTextColor: Color = InTouchTheme.colors.input,
    disableTextColor: Color = InTouchTheme.colors.mainGreen40,
    borderStrokeColor: Color = InTouchTheme.colors.mainGreen,
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    shape: Shape = RoundedCornerShape(20.dp),
) {
    Button(
        shape = shape,
        modifier = modifier,
        contentPadding = contentPadding,
        border = if (isHasStroke) {
            BorderStroke(1.dp, borderStrokeColor)
        } else {
            BorderStroke(
                0.dp,
                if (isEnabled) enableBackgroundColor else disableBackgroundColor
            )
        },
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
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = InTouchTheme.colors.input
        )
    }
}

@Composable
fun IconicButtonCircle(
    onClick: () -> Unit,
    modifier: Modifier,
    isEnabled: Boolean = true,
) {
    IntouchIconButton(
        onClick = onClick,
        modifier = modifier
            .width(84.dp)
            .height(81.dp),
        isEnabled = isEnabled,
        icon = R.drawable.icon_feather_in_circle,
        enableBackgroundColor = InTouchTheme.colors.mainGreen,
        disableBackgroundColor = InTouchTheme.colors.mainGreen40,
        enableTextColor = InTouchTheme.colors.textBlue,
        disableTextColor = InTouchTheme.colors.textBlue,
    )
}

@Composable
fun IconicTabBarPlus(
    onClick: () -> Unit,
    modifier: Modifier,
    isEnabled: Boolean = true,
) {
    IntouchIconButton(
        onClick = onClick,
        modifier = modifier
            .width(70.dp)
            .height(70.dp),
        isEnabled = isEnabled,
        icon = R.drawable.icon_plus_large,
        enableBackgroundColor = InTouchTheme.colors.mainGreen,
        disableBackgroundColor = InTouchTheme.colors.mainGreen40,
        enableTextColor = InTouchTheme.colors.textBlue,
        disableTextColor = InTouchTheme.colors.textBlue,
    )
}

@Composable
fun IconicButtonClose(
    onClick: () -> Unit,
    modifier: Modifier,
    isEnabled: Boolean = true,
) {
    IntouchIconButton(
        onClick = onClick,
        modifier = modifier
            .width(47.dp)
            .height(47.dp),
        isEnabled = isEnabled,
        icon = R.drawable.icon_close,
        enableBackgroundColor = InTouchTheme.colors.mainGreen,
        disableBackgroundColor = InTouchTheme.colors.accentGreen50,
        enableTextColor = InTouchTheme.colors.textBlue,
        disableTextColor = InTouchTheme.colors.textBlue,
    )
}

@Preview(showBackground = true)
@Composable
fun IconicButtonCirclePreview() {
    InTouchTheme {
        IconicButtonCircle(
            onClick = {},
            modifier = Modifier,
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconicTabBarPlusPreview() {
    InTouchTheme {
        IconicTabBarPlus(
            onClick = {},
            modifier = Modifier,
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconicButtonClose() {
    InTouchTheme {
        IconicButtonClose(
            onClick = {},
            modifier = Modifier,
            isEnabled = true
        )
    }
}
