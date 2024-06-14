package care.intouch.app.feature.authorization.presentation.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.authorization.presentation.ui.viewModel.AuthViewModule
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.textFields.PasswordTextField

@Composable
fun AuthenticationScreen(
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    headBackGround: ImageVO = ImageVO.Resource(R.drawable.head_background_small),
    headBackGroundTint: Color = InTouchTheme.colors.mainBlue,
    logoBackGroundTint: Color = InTouchTheme.colors.mainGreen,
    inTouchLogo: ImageVO = ImageVO.Resource(R.drawable.icon_intouch_logo),
    viewModel: AuthViewModule = hiltViewModel(),
) {
    var passwordText by remember { mutableStateOf("") }
    var loginText by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isIconVisible by rememberSaveable { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            painter = headBackGround.painter(), contentDescription = "", tint = headBackGroundTint,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )

        Icon(
            painter = inTouchLogo.painter(), contentDescription = "", tint = logoBackGroundTint,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 83.dp)
        )

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 243.dp, start = 28.dp, end = 28.dp),
            text = "Welcome to INtouch!",
            style = InTouchTheme.typography.titleMedium,
            color = InTouchTheme.colors.textGreen
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 326.dp, start = 28.dp, end = 28.dp),
            text = "Sign in to continue",
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen,
        )
        PasswordTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            isPasswordVisible = isPasswordVisible,
            isPasswordVisibleIconVisible = isIconVisible,
            onPasswordVisibleIconClick = {
                isPasswordVisible = !isPasswordVisible
            },
            hint = StringVO.Plain("Password"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 160.dp)
        )
        PasswordTextField(
            value = loginText,
            onValueChange = { loginText = it },
            isPasswordVisible = true,
            isPasswordVisibleIconVisible = false,
            onPasswordVisibleIconClick = {
            },
            hint = StringVO.Plain("E-mail"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 236.dp)
        )
        SecondaryButtonDark(
            onClick = { onForgotPasswordClick.invoke() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            textStyle = InTouchTheme.typography.bodyBold,
            isEnabled = true,
            text = "Forgot password?",
            enableTextColor = InTouchTheme.colors.textGreen
        )
        PrimaryButtonGreen(
            onClick = {
                viewModel.getTokenAuth(loginText, passwordText)
                onLoginClick.invoke()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 85.dp, start = 69.5.dp, end = 69.5.dp),
            isEnabled = !loginText.isNullOrEmpty() and !passwordText.isNullOrEmpty(),
            text = "LOGIN",
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AuthenticationScreenPreview() {
    InTouchTheme {
        AuthenticationScreen(
            onForgotPasswordClick = {},
            onLoginClick = {}
        )
    }
}

