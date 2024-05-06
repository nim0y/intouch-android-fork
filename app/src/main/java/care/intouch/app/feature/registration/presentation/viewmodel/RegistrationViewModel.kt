package care.intouch.app.feature.registration.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.registration.presentation.ui.RegistrationScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(

) : ViewModel() {
    private var _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when(event) {
            is RegistrationEvent.OnGetUserInfo -> {
                getUserInfo(event.userId, event.token)
            }

            is RegistrationEvent.OnSetPassword -> {
                checkPassword(event.password, event.confirmPassword)
            }
        }
    }

    private fun getUserInfo(userId: String?, token: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //TODO get user's info
                _state.update { registrationState ->
                    registrationState.copy(
                        screenState = RegistrationScreenState.Registration,
                        userName = "UserName"
                    )
                }
            }
        }
    }

    private fun checkPassword(password: String, confirmPassword: String) {

    }
}