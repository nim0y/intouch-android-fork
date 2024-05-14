package care.intouch.app.feature.authorization.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.uikit.R
import care.intouch.app.feature.authorization.presentation.viewmodel.RegistrationEvent
import care.intouch.app.feature.authorization.presentation.viewmodel.RegistrationViewModel
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun RegistrationScreen(
    userId: String? = null,
    token: String? = null,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Log.d("TEST", "id: ${userId}  token: ${token}")
    viewModel.onEvent(RegistrationEvent.OnGetUserInfo(userId, token))

    Scaffold { paddingValues ->
        when (state.value.screenState) {
            RegistrationScreenState.Loading -> {

            }

            RegistrationScreenState.Registration -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(InTouchTheme.colors.white)
                        .padding(paddingValues),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(172.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = R.drawable.head_background_small),
                            contentDescription = null
                        )
                        Image(
                            painter = painterResource(id = R.drawable.head_logo),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    SetPasswordScreen(
                        userName = state.value.userName,
                        errorPassword = state.value.errorPassword,
                        errorPasswordText = state.value.errorPasswordText,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }

}