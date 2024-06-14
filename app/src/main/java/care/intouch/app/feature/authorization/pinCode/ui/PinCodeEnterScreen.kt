package care.intouch.app.feature.authorization.pinCode.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
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
import care.intouch.app.R
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField

@Preview
@Composable
fun PinCodeEnterScreen(
    onSaveClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    //viewModel: PinCodeEnterViewModel = hiltViewModel(),
) {
    //val state by viewModel.state.collectAsStateWithLifecycle()

    var pinCode by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    val openAlertDialog = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.height(76.dp),
            painter = painterResource(id = care.intouch.uikit.R.drawable.head_background_small),
            contentDescription = "header",
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.pin_code),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            Spacer(modifier = Modifier.height(38.dp))
            Text(
                text = stringResource(id = R.string.enter_pin_sub_title),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textBlue
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column {
                PinCodeInputField(value = pinCode, onValueChange = { pinCode = it })

//                when (state) {
//
//                    PinCodeEnterState.Confirmed -> {
//                        onSaveClick()
//                        Spacer(modifier = Modifier.height(28.dp))
//                    }
//
//                    PinCodeEnterState.Default -> {
//                        Spacer(modifier = Modifier.height(28.dp))
//                    }
//
//                    PinCodeEnterState.NotConfirmed -> {

                Spacer(modifier = Modifier.height(28.dp))
//                    }
//                }
            }

            IntouchButton(
                onClick = {
                    //  viewModel.onEvent(pinCode)
                    pinCode = ""
                },
                modifier = Modifier,
                text = stringResource(id = R.string.next_button),
                isEnabled = pinCode.length == 4
            )

            Spacer(modifier = Modifier.height(2.dp))
            SecondaryButtonDark(
                onClick = {
                    //viewModel.onEvent(PinCodeConfirmationEvent.Skip)
                    onSkipClick()
                },
                textStyle = InTouchTheme.typography.bodySemibold,
                modifier = Modifier,
                text = stringResource(id = R.string.forgot_pin_title),
            )
            // Dialog(onDismissRequest = { }) {}
        }
    }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Dialog() {
    InTouchTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .height(304.dp)
                    .width(334.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(InTouchTheme.colors.dialog),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    style = InTouchTheme.typography.bodyBold,
                    color = InTouchTheme.colors.textBlue,
                    text = stringResource(id = R.string.forgot_your_pin_title)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    style = InTouchTheme.typography.bodySemibold,
                    color = InTouchTheme.colors.textGreen,
                    text = stringResource(id = R.string.create_new_pin_title),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(28.dp))

                IntouchButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                    text = stringResource(id = R.string.log_in_again),
                    contentPadding = PaddingValues(
                        horizontal = 78.dp, vertical = 14.dp
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                PrimaryButtonWhite(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp),
                    text = stringResource(id = R.string.cancel_button),
                    
                )
            }
        }

    }
}