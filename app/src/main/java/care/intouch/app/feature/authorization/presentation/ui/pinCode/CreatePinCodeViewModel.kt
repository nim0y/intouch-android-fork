package care.intouch.app.feature.authorization.presentation.ui.pinCode

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreatePinCodeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CreatePinCodeScreenState.Initial)

    val state: StateFlow<CreatePinCodeScreenState> = _state.asStateFlow()
    private var tempPinCode: String? = null

    fun onEvent(event: CreatePinCodeEvent) {
        when (event) {
            is CreatePinCodeEvent.Init -> {
                if (event.pinCode.isNullOrEmpty()) {
                    _state.update { CreatePinCodeScreenState.Error }
                }
                tempPinCode = event.pinCode
            }

            is CreatePinCodeEvent.Statement -> {
                if (tempPinCode == event.pinCode) {
                    _state.update { CreatePinCodeScreenState.Confirmed }
                } else {
                    _state.update { CreatePinCodeScreenState.NotConfirmed }
                }
            }
        }
    }
}