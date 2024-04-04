import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.app.UikitSampleButton
import care.intouch.app.ui.uiKitSamples.ScreenSample
import care.intouch.app.ui.uiKitSamples.samples.ButtonSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.CheckmarkSample
import care.intouch.app.ui.uiKitSamples.samples.MultilineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.NavigationSample
import care.intouch.app.ui.uiKitSamples.samples.OneLineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.PasswordTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.SliderSample
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

                UikitSampleButton(
                    text = "Go to checkmark sample",
                    onClick = { screenSample = ScreenSample.CheckmarkSample }
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
            NavigationSample()
        }

        ScreenSample.ToggleSample -> {
            ToggleSampleScreen()
        }

        ScreenSample.PasswordInputSample -> {
            PasswordTextFieldSampleScreen()
        }

        ScreenSample.SliderSample -> {
            SliderSample()
        }

        ScreenSample.CheckmarkSample -> {
            CheckmarkSample()
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

