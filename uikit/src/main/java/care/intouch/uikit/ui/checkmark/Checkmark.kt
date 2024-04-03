package care.intouch.uikit.ui.checkmark

import android.annotation.SuppressLint
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.theme.InTouchTheme

@SuppressLint("RememberReturnType")
@Composable
fun SimpleCheckmark(
    isChecked: Boolean = true,
    checkedColor: Color = InTouchTheme.colors.accentGreen,
    uncheckedColor: Color = InTouchTheme.colors.accentGreen30,
    onChangeState: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(isChecked) }

    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = it
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
fun SimpleCheckedCheckmarkPreview() {
    InTouchTheme {
        SimpleCheckmark(
            isChecked = true,
            onChangeState = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SimpleUncheckedCheckmarkPreview() {
    InTouchTheme {
        SimpleCheckmark(
            isChecked = false,
            onChangeState = {}
        )
    }
}


