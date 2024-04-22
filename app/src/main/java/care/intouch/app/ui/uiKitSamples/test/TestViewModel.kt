package care.intouch.app.ui.uiKitSamples.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cashadvisor.common.utill.extensions.logDebugError
import care.intouch.app.feature.authorization.domain.impl.ConfirmEmailUseCase
import care.intouch.app.feature.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCase
) : ViewModel() {
    fun confirmEmail() {
        viewModelScope.launch {
            val data = confirmEmailUseCase(id = 33, token = "qwerty")
            when (data) {
                is Resource.Error -> logDebugError("confirmEmailUseCase error =  ${data.error}")
                is Resource.Success -> logDebugError("confirmEmailUseCase success = ${data.data}")
            }
        }
    }
}