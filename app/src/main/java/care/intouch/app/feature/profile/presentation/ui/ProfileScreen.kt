package care.intouch.app.feature.profile.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun ProfileScreen(
    goToPasswordChangeScreen: () -> Unit,
    goToPinCodeChangeScreen: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp),
            text = "ProfileScreen",
            style = InTouchTheme.typography.titleMedium
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    goToPasswordChangeScreen.invoke()
                }
            ) {
                Text(text = "Go to PasswordChangeScreen")
            }

            Button(
                onClick = {
                    goToPinCodeChangeScreen.invoke()
                }
            ) {
                Text(text = "Go to PinCodeChangeScreen")
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    InTouchTheme {
        ProfileScreen(
            goToPasswordChangeScreen = {},
            goToPinCodeChangeScreen = {}
        )
    }
}