package care.intouch.app.feature.authorization.presentation.ui.pinCode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField


@Composable
fun PinCodeConfirmationScreen(
    onSaveClick: () -> Unit,
    onSkipClick: () -> Unit,
    pinCodeInst: String?,
    modifier: Modifier = Modifier,
    viewModel: CreatePinCodeViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.onEvent(CreatePinCodeEvent.Init(pinCodeInst))

    var pinCode by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input)
    ) {
        Image(
            modifier = Modifier
                .height(150.dp)
                .padding(bottom = 70.dp),
            painter = painterResource(id = R.drawable.head_background_small),
            contentDescription = "header",
            contentScale = ContentScale.FillBounds,
        )


        LaunchedEffect(key1 = Unit) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = care.intouch.app.R.string.confirm_pin_title),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            Spacer(modifier = Modifier.height(88.dp))
            Text(
                text = stringResource(id = care.intouch.app.R.string.enter_pin_sub_title),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textBlue
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column {
                PinCodeInputField(value = pinCode, onValueChange = { pinCode = it })

                when (state) {

                    CreatePinCodeScreenState.Confirmed -> {
                        onSaveClick()
                        Spacer(modifier = Modifier.height(28.dp))

                    }

                    CreatePinCodeScreenState.Error -> {
                        Snackbar { Text(text = "Пришел пустой или null pincode") }
                    }

                    CreatePinCodeScreenState.Default -> {
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    CreatePinCodeScreenState.NotConfirmed -> {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            modifier = Modifier.height(21.dp),
                            text = stringResource(id = care.intouch.app.R.string.passwords_do_not_match),
                            style = InTouchTheme.typography.caption1Semibold,
                            textAlign = TextAlign.Start,
                            color = InTouchTheme.colors.errorRed
                        )

                        Spacer(modifier = Modifier.height(11.dp))
                    }
                }
            }


            IntouchButton(
                onClick = {
                    viewModel.onEvent(CreatePinCodeEvent.Statement(pinCode))
                    pinCode = ""
                },
                modifier = Modifier,
                text = stringResource(id = care.intouch.app.R.string.save_button),
                isEnabled = pinCode.length == 4
            )

            Spacer(modifier = Modifier.height(2.dp))
            PrimaryButtonWhite(
                onClick = { onSkipClick() },
                modifier = Modifier,
                text = stringResource(id = care.intouch.app.R.string.skip_button)
            )
        }
    }
}