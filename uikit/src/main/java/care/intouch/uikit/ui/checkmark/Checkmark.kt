package care.intouch.uikit.ui.checkmark

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@SuppressLint("RememberReturnType")
@Composable
fun Checkmark(
    isChecked: Boolean = true,
    checkedColor: Color = InTouchTheme.colors.accentGreen,
    uncheckedColor: Color = InTouchTheme.colors.accentGreen30,
    onChangeState: (Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(isChecked) }

    Checkbox(
        checked = checkedState,
        onCheckedChange = {
            checkedState = it
            onChangeState.invoke(it)
        },
        colors = CheckboxDefaults.colors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        ),
    )
}

@Composable
@Preview(showBackground = true)
fun CheckedCheckmarkPreview() {
    InTouchTheme {
        Checkmark(
            isChecked = true,
            onChangeState = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun UncheckedCheckmarkPreview() {
    InTouchTheme {
        Checkmark(
            isChecked = false,
            onChangeState = {}
        )
    }
}

@Composable
fun CheckmarkWithText(
    modifier: Modifier = Modifier,
    isChecked: Boolean = true,
    text: String,
    checkedColor: Color = InTouchTheme.colors.accentGreen,
    uncheckedColor: Color = InTouchTheme.colors.accentGreen30,
    colorText: Color = InTouchTheme.colors.textBlue,
    textStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    onChangeState: (Boolean) -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkmark(
            isChecked = isChecked,
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor,
            onChangeState = onChangeState
        )

        Text(
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


