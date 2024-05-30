package care.intouch.app.care.intouch.app.feature.authorization.pinCode.presentation.createPinCode

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

    fun event(event: CreatePinCodeEvent) {
        when(event){
            is CreatePinCodeEvent.Create -> {
                tempPinCode = event.pinCode
                _state.update { CreatePinCodeScreenState.Confirm }
            }
            is CreatePinCodeEvent.Confirm -> {
                if (tempPinCode == event.pinCode){
                    _state.update { CreatePinCodeScreenState.ConfirmSuccess }
                }else{
                    _state.update { CreatePinCodeScreenState.Error }
                }
            }
        }
    }
}