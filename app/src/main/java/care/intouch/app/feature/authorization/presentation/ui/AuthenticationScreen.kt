package care.intouch.app.feature.authorization.presentation.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
    val state by viewModel.uiState.collectAsState()
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
            text = stringResource(care.intouch.app.R.string.welcome_to_intouch),
            style = InTouchTheme.typography.titleMedium,
            color = InTouchTheme.colors.textGreen
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 326.dp, start = 28.dp, end = 28.dp),
            text = stringResource(care.intouch.app.R.string.sign_in_to_continue),
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen,
        )
        PasswordTextField(
            value = state.password,
            onValueChange = { viewModel.savePassword(it) },
            isPasswordVisible = state.isPasswordVisible,
            isPasswordVisibleIconVisible = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            error = viewModel.passwordErrorState(),
            caption = viewModel.passwordValidation(),
            onPasswordVisibleIconClick = {
                viewModel.onPasswordIconClick()
            },
            hint = StringVO.Plain(stringResource(care.intouch.app.R.string.password)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 160.dp)
        )
        PasswordTextField(
            value = state.login,
            onValueChange = { viewModel.saveLogin(it) },
            isPasswordVisible = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            error = viewModel.loginErrorState(),
            caption = viewModel.loginValidation(),
            isPasswordVisibleIconVisible = false,
            onPasswordVisibleIconClick = {
            },
            hint = StringVO.Plain(stringResource(care.intouch.app.R.string.e_mail)),
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
            text = stringResource(id = care.intouch.app.R.string.forgot_password),
            enableTextColor = InTouchTheme.colors.textGreen
        )
        PrimaryButtonGreen(
            onClick = {
                viewModel.loginByEmail(state.login, state.password)
                onLoginClick.invoke()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 85.dp, start = 69.5.dp, end = 69.5.dp),
            isEnabled = !state.login.isNullOrEmpty() and !state.password.isNullOrEmpty(),
            text = stringResource(care.intouch.app.R.string.login),
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

