package care.intouch.uikit.ui.checkmark

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme
import kotlinx.coroutines.delay

@Composable
fun Checkmark(
    isChecked: Boolean = true,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    size: Dp = 24.dp,
    errorColor: Color = Color.Red,
    uncheckedColor: Color = InTouchTheme.colors.mainGreen40,
    checkDrawableResource: Painter = painterResource(id = R.drawable.icon_checkmark_is_checked),
    callbackState: (Boolean) -> Unit,
    onChangeState: (Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .clickable {
                if (isEnabled) {
                    callbackState.invoke(!isChecked)
                    onChangeState.invoke(isChecked)
                }

            }
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(color = Color.Transparent, shape = RoundedCornerShape(5.dp))
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isError) errorColor else uncheckedColor
                    ),
                    shape = RoundedCornerShape(5.dp)
                )
        )

        if (isChecked) {
            Image(
                modifier = Modifier.size(size),
                painter = checkDrawableResource,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CheckmarkPreview() {
    var checkedState by remember {
        mutableStateOf(true)
    }

    Checkmark(
        isChecked = checkedState,
        callbackState = {
            checkedState = it
        },
        onChangeState = {}
    )
}

@Composable
fun CheckmarkWithText(
    modifier: Modifier = Modifier,
    isChecked: Boolean = true,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    text: String,
    checkmarkSize: Dp = 24.dp,
    uncheckedDisabledColor: Color = InTouchTheme.colors.mainGreen40,
    checkDrawableResource: Painter = painterResource(id = R.drawable.icon_checkmark_is_checked),
    enabledColorText: Color = InTouchTheme.colors.textBlue,
    errorColorText: Color = Color.Red,
    textStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    callbackState: (Boolean) -> Unit = {},
    onChangeState: (Boolean) -> Unit
) {

    val defaultColorText = when {
        !isEnabled -> uncheckedDisabledColor
        else -> enabledColorText
    }

    Row(
        modifier = modifier
            .height(45.dp)
            .padding(start = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkmark(
            isChecked = isChecked,
            isError = isError,
            isEnabled = isEnabled,
            size = checkmarkSize,
            uncheckedColor = uncheckedDisabledColor,
            checkDrawableResource = checkDrawableResource,
            callbackState = callbackState,
            onChangeState = {
                if (isEnabled) {
                    onChangeState.invoke(it)
                }
            }
        )

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = text,
            color = if (isError && !isChecked) errorColorText else defaultColorText,
            style = textStyle
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CheckmarkWithTextPreview() {

    var checkedState by remember {
        mutableStateOf(false)
    }

    var errorState by remember {
        mutableStateOf(false)
    }

    var enabledState by remember {
        mutableStateOf(false)
    }

    InTouchTheme {
        CheckmarkWithText(
            modifier = Modifier.width(260.dp),
            text = "Pursuing further education or certifications",
            isChecked = checkedState,
            isError = errorState,
            isEnabled = enabledState,
            callbackState = {
                checkedState = it
            },
            onChangeState = {}
        )
    }
}


