package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.buttons.PrimaryButtonStroke
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite

@Composable
fun ButtonSampleScreen() {
    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            PrimaryButtonGreen(
                onClick = { },
                modifier = Modifier.padding(top = 16.dp),
                text = "Call to action"
            )
            PrimaryButtonGreen(
                onClick = { },
                modifier = Modifier.padding(top = 16.dp),
                text = "Call to action",
                isEnabled = false
            )
            PrimaryButtonStroke(
                onClick = {},
                modifier = Modifier.padding(top = 16.dp),
                text = "Call to action"
            )
            PrimaryButtonWhite(
                onClick = {},
                modifier = Modifier.padding(top = 16.dp),
                text = "Call to action"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonSampleScreenPreview() {
    InTouchTheme {
        ButtonSampleScreen()
    }
}