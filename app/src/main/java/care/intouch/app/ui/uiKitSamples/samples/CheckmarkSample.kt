package care.intouch.app.ui.uiKitSamples.samples

import android.text.BoringLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.checkmark.CheckWid
import care.intouch.uikit.ui.checkmark.Checkmark
import care.intouch.uikit.ui.checkmark.CheckmarkWithText

@Composable
fun CheckmarkSample() {

    var checkedState by remember {
        mutableStateOf(true)
    }

    var errorState by remember {
        mutableStateOf(false)
    }

    var enabledState by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CheckmarkWithText(
                isChecked = checkedState,
                isError = errorState,
                isEnabled = enabledState,
                text = "Some long label text",
                callbackState = {
                    checkedState = it
                },
                onChangeState = {}
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SwitchWithLabel(
                    text = "Check",
                    checkState = checkedState,
                    onSwitchChange = {
                        checkedState = !checkedState
                    }
                )

                SwitchWithLabel(
                    text = "Error",
                    checkState = errorState,
                    onSwitchChange = {
                        errorState = !errorState
                    }
                )

                SwitchWithLabel(
                    text = "Enable",
                    checkState = enabledState,
                    onSwitchChange = {
                        enabledState = !enabledState
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CheckmarkSamplePreview() {
    InTouchTheme {
        CheckmarkSample()
    }
}

@Composable
fun SwitchWithLabel(
    text: String,
    checkState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {

    var check by remember {
        mutableStateOf(checkState)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
        Switch(
            checked = checkState,
            onCheckedChange = {
                check = it
                onSwitchChange.invoke(it)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SwitchWithLabelPreview() {
    InTouchTheme {
        SwitchWithLabel(
            text = "Label",
            checkState = true,
            onSwitchChange = {}
        )
    }
}



