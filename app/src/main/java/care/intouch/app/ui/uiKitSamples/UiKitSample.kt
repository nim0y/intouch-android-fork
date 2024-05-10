import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.ui.uiKitSamples.ScreenSample
import care.intouch.app.ui.uiKitSamples.samples.ButtonSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.MultilineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.NavigationAppSample
import care.intouch.app.ui.uiKitSamples.samples.OneLineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.PasswordTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.RegularChipsSample
import care.intouch.app.ui.uiKitSamples.samples.SliderSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.ToggleSampleScreen
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun UiKitSample() {

    var screenSample by remember {
        mutableStateOf<ScreenSample>(ScreenSample.MainSampleMenu)
    }

    when (screenSample) {
        ScreenSample.MainSampleMenu -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

               UikitSampleButton(
                    text = "Go to custom buttons sample",
                    onClick = { screenSample = ScreenSample.ButtonsSample }
                )

                UikitSampleButton(
                    text = "Go to one line text field sample",
                    onClick = { screenSample = ScreenSample.OneLineTexFieldSample }
                )

                UikitSampleButton(
                    text = "Go to multiline text field sample",
                    onClick = { screenSample = ScreenSample.MultilineTexFieldSample }
                )

                UikitSampleButton(
                    text = "Go to navigation sample",
                    onClick = { screenSample = ScreenSample.NavigationSample }
                )

                UikitSampleButton(
                    text = "Go to chips sample",
                    onClick = { screenSample = ScreenSample.ChipsSample }
                    )
                
                UikitSampleButton(
                    text = "Go to toggle sample",
                    onClick = { screenSample = ScreenSample.ToggleSample }
                )

                UikitSampleButton(
                    text = "Go to password text field sample",
                    onClick = { screenSample = ScreenSample.PasswordInputSample }
                )

                UikitSampleButton(
                    text = "Go to slider sample",
                    onClick = { screenSample = ScreenSample.SliderSample }
                )
            }
        }

        ScreenSample.ButtonsSample -> {
            ButtonSampleScreen()
        }
        ScreenSample.OneLineTexFieldSample -> {
            OneLineTextFieldSampleScreen()
        }

        ScreenSample.MultilineTexFieldSample -> {
            MultilineTextFieldSampleScreen()
        }

        ScreenSample.NavigationSample -> {
            NavigationAppSample()
        }

        ScreenSample.ChipsSample -> {
            RegularChipsSample()
        }

        ScreenSample.ToggleSample -> {
            ToggleSampleScreen()
        }

        ScreenSample.PasswordInputSample -> {
            PasswordTextFieldSampleScreen()
        }

        ScreenSample.SliderSample -> {
            SliderSampleScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiKitSamplePreview() {
    InTouchTheme {
        UiKitSample()
    }
}

@Composable
fun UikitSampleButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonColors(
            containerColor = InTouchTheme.colors.mainGreen,
            contentColor = InTouchTheme.colors.input,
            disabledContainerColor = InTouchTheme.colors.mainGreen,
            disabledContentColor = InTouchTheme.colors.mainGreen,
        ),
        onClick = { onClick.invoke() }
    ) {
        Text(
            text = text,
            style = InTouchTheme.typography.bodyRegular,
        )
    }
}

