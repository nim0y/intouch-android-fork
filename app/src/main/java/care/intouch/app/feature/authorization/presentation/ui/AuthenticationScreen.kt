package care.intouch.app.feature.authorization.presentation.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import care.intouch.app.feature.authorization.presentation.ui.models.AuthScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.AuthenticationDataEvent
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
    viewModel: AuthViewModule,
) {
    val state by viewModel.uiState.collectAsState()
    AuthenticationScreen(
        onForgotPasswordClick = onForgotPasswordClick,
        onLoginClick = onLoginClick,
        state = state,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
private fun AuthenticationScreen(
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    headBackGround: ImageVO = ImageVO.Resource(R.drawable.head_background_small),
    headBackGroundTint: Color = InTouchTheme.colors.mainBlue,
    logoBackGroundTint: Color = InTouchTheme.colors.mainGreen,
    inTouchLogo: ImageVO = ImageVO.Resource(R.drawable.icon_intouch_logo),
    state: AuthScreenState,
    onEvent: (AuthenticationDataEvent) -> Unit,
) {
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(83.dp))
            Icon(
                painter = inTouchLogo.painter(), contentDescription = "", tint = logoBackGroundTint,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(75.dp))
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.welcome_to_intouch).value(),
                style = InTouchTheme.typography.titleMedium,
                color = InTouchTheme.colors.textGreen,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.sign_in_to_continue).value(),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = state.login,
                onValueChange =
                {
                    onEvent(AuthenticationDataEvent.OnLoginTextChanged(it))
                    onEvent(AuthenticationDataEvent.OnLoginErrorChanged)
                    onEvent(AuthenticationDataEvent.OnLoginValidationChecked)
                },
                isPasswordVisible = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                error = state.isErrorLogin,
                caption = state.loginCaption,
                isPasswordVisibleIconVisible = false,
                onPasswordVisibleIconClick = {
                },
                hint = StringVO.Resource(care.intouch.app.R.string.e_mail),
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = state.password,
                onValueChange =
                {
                    onEvent(AuthenticationDataEvent.OnPasswordTextChanged(it))
                    onEvent(AuthenticationDataEvent.OnPasswordErrorChanged)
                    onEvent(AuthenticationDataEvent.OnPasswordValidationChecked)
                },
                isPasswordVisible = state.isPasswordVisible,
                isPasswordVisibleIconVisible = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                error = state.isErrorPassword,
                caption = state.passwordCaption,
                onPasswordVisibleIconClick = {
                    onEvent(AuthenticationDataEvent.OnPasswordIconClick)
                },
                hint = StringVO.Resource(care.intouch.app.R.string.password),
            )
            Spacer(modifier = Modifier.height(28.dp))
            PrimaryButtonGreen(
                onClick = {
                    onEvent(
                        AuthenticationDataEvent.OnLoginButtonClicked(
                            state.login,
                            state.password
                        )
                    )
                    onLoginClick.invoke()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                isEnabled =
                state.login.isNotEmpty() and state.password.isNotEmpty() and !state.isErrorLogin and !state.isErrorPassword,
                text = stringResource(care.intouch.app.R.string.login),
            )
            SecondaryButtonDark(
                onClick = { onForgotPasswordClick.invoke() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                textStyle = InTouchTheme.typography.bodyBold,
                isEnabled = true,
                text = stringResource(id = care.intouch.app.R.string.forgot_password),
                enableTextColor = InTouchTheme.colors.textGreen
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun AuthenticationScreenPreview() {
    val state: AuthScreenState = AuthScreenState(
        password = "",
        login = "",
        loginCaption = StringVO.Plain(""),
        passwordCaption = StringVO.Plain(""),
        isPasswordVisible = false,
        isIconVisible = true,
        isErrorLogin = false,
        isErrorPassword = false,
    )
    InTouchTheme {
        AuthenticationScreen(
            onForgotPasswordClick = {},
            onLoginClick = {},
            onEvent = {},
            state = state
        )
    }
}

