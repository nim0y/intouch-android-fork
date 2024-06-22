package care.intouch.app.feature.pinCode.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.pinCode.ui.models.PinCodeEvent
import care.intouch.app.feature.pinCode.ui.models.PinCodeSideEffect
import care.intouch.app.feature.pinCode.ui.models.PinCodeUIState
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField

@Composable
fun PinCodeInitScreen(
    modifier: Modifier = Modifier,
    viewModel: PinCodeInitViewModel = hiltViewModel(),
    onSkipClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    var pinCode by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                PinCodeSideEffect.Skip -> onSkipClick()
                PinCodeSideEffect.Save -> onSaveClick()
                PinCodeSideEffect.ResetPinCode -> pinCode = ""
            }
        }
    }

    PinCodeInitScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::onEvent,
        pinCode = pinCode,
        onPinCodeChanged = {
            pinCode = it
        }
    )
}

@Composable
fun PinCodeInitScreen(
    pinCode: String,
    onPinCodeChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    state: PinCodeUIState,
    onEvent: (PinCodeEvent) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input)
    ) {
        Image(
            modifier = Modifier.height(76.dp),
            painter = ImageVO.Resource(R.drawable.head_background_small).painter(),
            contentDescription = "header",
            contentScale = ContentScale.FillBounds,
        )
        if (state.isBackButtonVisible) {
            Icon(
                modifier = Modifier
                    .padding(start = 28.dp, top = 12.dp)
                    .clickable(enabled = state.isBackButtonEnabled) {
                        onEvent(PinCodeEvent.OnBackButtonClicked)
                    },
                painter = ImageVO.Resource(R.drawable.icon_arrow_left).painter(),
                contentDescription = null,
                tint = InTouchTheme.colors.mainGreen
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 34.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = state.title.value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            if (state.isDescriptionVisible) {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = state.description.value(),
                    style = InTouchTheme.typography.bodyRegular,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(28.dp))
            } else {
                Spacer(modifier = Modifier.height(60.dp))

            }
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.enter_pin_sub_title).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textBlue
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column {
                PinCodeInputField(
                    value = pinCode,
                    onValueChange = {
                        onPinCodeChanged(it)
                        onEvent(PinCodeEvent.OnPinCodeChanged(it))
                    }
                )
            }
            if (state.isError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.height(21.dp),
                    text = state.errorMessage.value(),
                    style = InTouchTheme.typography.caption1Semibold,
                    textAlign = TextAlign.Start,
                    color = InTouchTheme.colors.errorRed
                )
                Spacer(modifier = Modifier.height(11.dp))
            } else {
                Spacer(modifier = Modifier.height(28.dp))
            }
            IntouchButton(
                onClick = {
                    onEvent(PinCodeEvent.OnSaveButtonClicked)
                },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.save_button).value(),
                isEnabled = state.isSaveButtonEnabled
            )

            Spacer(modifier = Modifier.height(2.dp))
            PrimaryButtonWhite(
                onClick = {
                    onEvent(PinCodeEvent.OnSkipButtonClicked)
                },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.skip_button).value(),
                isEnabled = state.isSkipButtonEnabled
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    InTouchTheme {
        PinCodeInitScreen(
            state = PinCodeUIState(
                title = StringVO.Resource(care.intouch.app.R.string.confirm_pin_title),
                isBackButtonVisible = true,
                isBackButtonEnabled = true,
                description = StringVO.Resource(care.intouch.app.R.string.info_about_setup_pin),
                isDescriptionVisible = true,
                isError = true,
                errorMessage = StringVO.Plain(""),
                isSaveButtonEnabled = false,
                isSkipButtonEnabled = true
            ),
            pinCode = "5432",
            onPinCodeChanged = {}
        )
    }
}