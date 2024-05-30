package care.intouch.app.care.intouch.app.feature.authorization.pinCode.presentation.createPinCode

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import care.intouch.app.R
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CreatePinCodeScreen(
    viewModel: CreatePinCodeViewModel = hiltViewModel(), modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input)
    ) {
        Image(
            modifier = Modifier
                .height(150.dp)
                .padding(bottom = 70.dp),
            painter = painterResource(id = care.intouch.uikit.R.drawable.head_background_small),
            contentDescription = "header",
            contentScale = ContentScale.FillBounds,
        )
        when (state) {
            CreatePinCodeScreenState.Confirm -> {
                Log.d("TAG", "CreatePinCodeScreenState.Confirm")
                ConfirmContent(clickToConfirm = viewModel::onEvent)
            }

            CreatePinCodeScreenState.Create -> {
                Log.d("TAG", "CreatePinCodeScreenState.Create")
                CreatePinCodeContent(clickToCreate = viewModel::onEvent)
            }

            CreatePinCodeScreenState.Error -> {
                Log.d("TAG", "CreatePinCodeScreenState.Error")
                ErrorConfirmContent(clickToConfirm = viewModel::onEvent)
            }

            CreatePinCodeScreenState.ConfirmSuccess -> {
                Log.d("TAG", "CreatePinCodeScreenState.ConfirmSuccess")
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
fun CreatePinCodeContent(
    modifier: Modifier = Modifier,
    clickToCreate: (event: CreatePinCodeEvent) -> Unit = {},

    ) {

    var pinCode by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier.fillMaxSize().focusRequester(focusRequester),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.create_pin_title),
            style = InTouchTheme.typography.bodyRegular,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            color = InTouchTheme.colors.textBlue
        )

        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = stringResource(id = R.string.info_about_setup_pin),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )

        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = stringResource(id = R.string.enter_pin_sub_title),
            style = InTouchTheme.typography.bodyRegular,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
        PinCodeInputField(
            value = pinCode,
            onValueChange = { pinCode = it })

        Spacer(modifier = Modifier.height(28.dp))
        IntouchButton(
            onClick = { clickToCreate(CreatePinCodeEvent.Create(pinCode)) },
            modifier = Modifier,
            text = stringResource(id = R.string.save_button),
            isEnabled = pinCode.length == 4
        )

        Spacer(modifier = Modifier.height(2.dp))
        PrimaryButtonWhite(
            onClick = { /*TODO*/ },
            modifier = Modifier,
            text = stringResource(id = R.string.skip_button)
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
fun ConfirmContent(
    modifier: Modifier = Modifier,
    clickToConfirm: (createPinCodeEvent: CreatePinCodeEvent) -> Unit = {}
) {
    var pinCode by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier.fillMaxSize().focusRequester(focusRequester),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.confirm_pin_title),
            style = InTouchTheme.typography.bodyRegular,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            color = InTouchTheme.colors.textBlue
        )

        Spacer(modifier = Modifier.height(88.dp))
        Text(
            text = stringResource(id = R.string.enter_pin_sub_title),
            style = InTouchTheme.typography.bodyRegular,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
        PinCodeInputField(value = pinCode, onValueChange = { pinCode = it })

        Spacer(modifier = Modifier.height(28.dp))
        IntouchButton(
            onClick = { clickToConfirm(CreatePinCodeEvent.Confirm(pinCode)) },
            modifier = Modifier,
            text = stringResource(id = R.string.save_button),
            isEnabled = pinCode.length == 4
        )

        Spacer(modifier = Modifier.height(2.dp))
        PrimaryButtonWhite(
            onClick = { /*TODO*/ },
            modifier = Modifier,
            text = stringResource(id = R.string.skip_button)
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFFFFFFF)
fun ErrorConfirmContent(
    modifier: Modifier = Modifier,
    clickToConfirm: (createPinCodeEvent: CreatePinCodeEvent) -> Unit = {}
) {
    var pinCode by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier.fillMaxSize().focusRequester(focusRequester),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.confirm_pin_title),
            style = InTouchTheme.typography.bodyRegular,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            color = InTouchTheme.colors.textBlue
        )

        Spacer(modifier = Modifier.height(88.dp))
        Text(
            text = stringResource(id = R.string.enter_pin_sub_title),
            style = InTouchTheme.typography.bodyRegular,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
        PinCodeInputField(
            value = pinCode, onValueChange = { pinCode = it }, isError = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.passwords_do_not_match),
            style = InTouchTheme.typography.caption1Semibold,
            textAlign = TextAlign.Start,
            color = InTouchTheme.colors.errorRed
        )

        Spacer(modifier = Modifier.height(11.dp))

        IntouchButton(
            onClick = {
                clickToConfirm(CreatePinCodeEvent.Confirm(pinCode))
                pinCode = ""
            },
            modifier = Modifier,
            text = stringResource(id = R.string.save_button),
            isEnabled = pinCode.length == 4
        )

        Spacer(modifier = Modifier.height(2.dp))
        PrimaryButtonWhite(
            onClick = { /*TODO*/ },
            modifier = Modifier,
            text = stringResource(id = R.string.skip_button)
        )
    }
}