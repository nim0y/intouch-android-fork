package care.intouch.app.feature.authorization.pinCode.ui

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.authorization.pinCode.data.PinCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PinCodeEnterViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PinCodeEnterState.Initial)

    val state: StateFlow<PinCodeEnterState> = _state.asStateFlow()


    fun onEvent(pincode: String){

    }
}