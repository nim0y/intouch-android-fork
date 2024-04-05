package care.intouch.uikit.ui.checkmark

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun Checkmark(
    isChecked: Boolean = true,
    size: Dp = 24.dp,
    uncheckedColor: Color = InTouchTheme.colors.mainGreen40,
    checkDrawableResource: Painter = painterResource(id = R.drawable.icon_checkmark_is_checked),
    onChangeState: (Boolean) -> Unit
) {
    var checkState by remember {
        mutableStateOf(isChecked)
    }

    Box(
        modifier = Modifier
            .clickable {
                checkState = !checkState
                onChangeState.invoke(checkState)
            }
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(color = Color.Transparent, shape = RoundedCornerShape(5.dp))
                .border(
                    border = BorderStroke(width = 1.dp, color = uncheckedColor),
                    shape = RoundedCornerShape(5.dp)
                )
        )

        if (checkState) {
            Box(
            ) {
                Image(
                    modifier = Modifier.size(size),
                    painter = checkDrawableResource,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CheckmarkPreview() {
    InTouchTheme {
        Checkmark(
            isChecked = true,
        ) {}
    }
}

@Composable
fun CheckmarkWithText(
    modifier: Modifier = Modifier,
    isChecked: Boolean = true,
    text: String,
    checkmarkSize: Dp = 24.dp,
    uncheckedColor: Color = InTouchTheme.colors.mainGreen40,
    checkDrawableResource: Painter = painterResource(id = R.drawable.icon_checkmark_is_checked),
    colorText: Color = InTouchTheme.colors.textBlue,
    textStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    onChangeState: (Boolean) -> Unit
) {

    Row(
        modifier = modifier
            .height(45.dp)
            .padding(start = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkmark(
            isChecked = isChecked,
            size = checkmarkSize,
            uncheckedColor = uncheckedColor,
            checkDrawableResource = checkDrawableResource,
            onChangeState = onChangeState
        )

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = text,
            color = colorText,
            style = textStyle
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CheckmarkWithTextPreview() {
    InTouchTheme {
        CheckmarkWithText(
            modifier = Modifier.width(260.dp),
            text = "Pursuing further education or certifications",
            onChangeState = {}
        )
    }
}


