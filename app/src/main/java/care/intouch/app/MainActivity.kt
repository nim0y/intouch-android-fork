package care.intouch.app

import UiKitSample
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.EntryPoint
import care.intouch.app.core.navigation.navhost.AuthorizationNavHost
import care.intouch.app.core.navigation.Route
import care.intouch.uikit.theme.InTouchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InTouchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = InTouchTheme.colors.mainBlue
                ) {
                    var movedUiKitSample by remember {
                        mutableStateOf(false)
                    }

                    var navIsShowed by remember {
                        mutableStateOf(false)
                    }

                    var entryPoint: EntryPoint by remember {
                        mutableStateOf(EntryPoint.Authentication())
                    }

                    val navController = rememberNavController()

                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!navIsShowed) {
                            if (BuildConfig.DEBUG) {
                                MainScreenWithDebug(
                                    movedUiKitSample = movedUiKitSample,
                                    onChangeState = { movedUiKitSample = !movedUiKitSample }
                                )
                            } else {
                                Greeting("Android")
                            }

                            if (!movedUiKitSample) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Choose entry point for navigation",
                                        style = InTouchTheme.typography.titleMedium
                                    )
                                    Text(text = entryPoint.stringValue)
                                    Switch(
                                        checked = entryPoint.boolValue,
                                        onCheckedChange = {
                                            entryPoint =
                                                if (it) EntryPoint.Authentication() else EntryPoint.Registration()
                                        }
                                    )
                                }

                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    onClick = {
                                        navIsShowed = !navIsShowed
                                    }
                                ) {
                                    Text("Go to navigation")
                                }
                            }
                        } else {

                            when (entryPoint) {
                                is EntryPoint.Authentication -> {
                                    AuthorizationNavHost(
                                        navController = navController,
                                        startDestination = Route.AUTHENTICATION
                                    )
                                }

                                is EntryPoint.Registration -> {
                                    AuthorizationNavHost(
                                        navController = navController,
                                        startDestination = Route.REGISTRATION
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        style = InTouchTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun MainScreenWithDebug(movedUiKitSample: Boolean, onChangeState: () -> Unit) {
    if (movedUiKitSample) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                UiKitSample()
            }
            UikitSampleButton(
                text = stringResource(R.string.main_screen_button),
                onClick = { onChangeState.invoke() }
            )
        }

    } else {
        Greeting("Android")
        UikitSampleButton(
            text = stringResource(R.string.uikit_sample_button),
            onClick = { onChangeState.invoke() }
        )
    }
}

@Composable
fun UikitSampleButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonColors(
            containerColor = InTouchTheme.colors.mainGreen,
            contentColor = InTouchTheme.colors.input,
            disabledContainerColor = InTouchTheme.colors.mainGreen,
            disabledContentColor = InTouchTheme.colors.mainGreen,
        ),
        onClick = { onClick.invoke() }
    ) {
        Text(
            text = text,
            style = InTouchTheme.typography.bodyRegular,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InTouchTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = InTouchTheme.colors.mainBlue,
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Greeting("Android")
            UikitSampleButton(
                text = "UiKitSample",
                onClick = {}
            )
        }
    }
}
