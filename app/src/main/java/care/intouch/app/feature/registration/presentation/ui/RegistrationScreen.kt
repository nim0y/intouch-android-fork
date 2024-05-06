package care.intouch.app.feature.registration.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.registration.presentation.viewmodel.RegistrationEvent
import care.intouch.app.feature.registration.presentation.viewmodel.RegistrationViewModel

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
        when(state.value.screenState) {
            RegistrationScreenState.Loading -> {

            }

            RegistrationScreenState.Registration -> {
                SetPasswordScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    userName = state.value.userName,
                    errorPassword = state.value.errorPassword,
                    errorPasswordText = state.value.errorPasswordText,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }

}