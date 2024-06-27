package care.intouch.app.feature.authorization.pinCode.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.pinCode.data.PinCodeRepository
import care.intouch.app.feature.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeEnterViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PinCodeEnterState.Initial)

    val state: StateFlow<PinCodeEnterState> = _state.asStateFlow()


    fun onEvent(pincode: String) {
        viewModelScope.launch {
            when (val result = repository.verifyPinCode(pincode)) {
                is Resource.Error -> _state.update {
                    Log.d("TAG", "Resource.Error")
                    PinCodeEnterState.NotConfirmed
                }

                is Resource.Success -> {
                    if (result.data) {
                        _state.update {
                            Log.d("TAG", "Resource.Success true")
                            PinCodeEnterState.Confirmed
                        }
                    } else {
                        _state.update {
                            Log.d("TAG", "Resource.Success else")
                            PinCodeEnterState.NotConfirmed
                        }
                    }
                }
            }
        }
    }
}