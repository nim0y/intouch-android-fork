package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.Checkbox

@Composable
fun CheckboxSampleScreen() {
    var isChecked by remember { mutableStateOf(true) }
    var isCheckedSecond by remember { mutableStateOf(false) }

    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Checkbox(
                isChecked = isChecked,
                text = "Answer 1"
            ) {
                isChecked = !isChecked
            }

            Checkbox(
                isChecked = isCheckedSecond,
                text = "Answer 2"
            ) {
                isCheckedSecond = !isCheckedSecond
            }
        }
    }
}