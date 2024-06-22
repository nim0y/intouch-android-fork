package care.intouch.app.feature.pinCode.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.pinCode.data.PinCodeRepository
import care.intouch.app.feature.pinCode.ui.models.PinCodeEvent
import care.intouch.app.feature.pinCode.ui.models.PinCodeScreenState
import care.intouch.app.feature.pinCode.ui.models.PinCodeSideEffect
import care.intouch.app.feature.pinCode.ui.models.PinCodeUIState
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeInitViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    private val _pinCodeScreenState = MutableStateFlow(PinCodeScreenState())
    private val pinCodeScreenState: StateFlow<PinCodeScreenState> = _pinCodeScreenState.asStateFlow()

    private val currentState get() = pinCodeScreenState.replayCache.firstOrNull() ?: PinCodeScreenState()

    private val _uiState = MutableStateFlow(PinCodeUIState())
    val uiState: StateFlow<PinCodeUIState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PinCodeSideEffect>()
    val sideEffect: SharedFlow<PinCodeSideEffect> = _sideEffect.asSharedFlow()

    fun onEvent(event: PinCodeEvent) {
        when (event) {
            is PinCodeEvent.OnPinCodeChanged -> updatePinCode(event.pinCode)
            PinCodeEvent.OnSaveButtonClicked -> savePinCode()
            PinCodeEvent.OnSkipButtonClicked -> skip()
            PinCodeEvent.OnBackButtonClicked -> navigateBack()
        }
    }

    init {
        viewModelScope.launch {
            pinCodeScreenState.collect { state ->
                with(state) {
                    _uiState.update {
                        it.copy(
                            title = if (isInitial) {
                                StringVO.Resource(R.string.create_pin_title)
                            } else {
                                StringVO.Resource(R.string.confirm_pin_title)
                            },
                            isDescriptionVisible = isInitial,
                            description = StringVO.Resource(R.string.info_about_setup_pin),
                            isBackButtonEnabled = !isInitial,
                            isBackButtonVisible = !isInitial,
                            isError = isError,
                            isSaveButtonEnabled = isValid,
                            errorMessage = StringVO.Resource(R.string.password_not_match_try_again_error),
                        )
                    }
                }
            }
        }
    }

    private fun updatePinCode(pinCode: String) {
        _pinCodeScreenState.update {
            it.copy(currentPinCode = pinCode)
        }
        val isValid = validatePinCodeLength(pinCode)
        _pinCodeScreenState.update {
            it.copy(isValid = isValid)
        }
    }

    private fun validatePinCodeLength(pinCode: String): Boolean {
        return pinCode.length == PIN_CODE_VALID_LENGTH
    }

    private fun savePinCode() {
        if (currentState.isInitial) {
            _pinCodeScreenState.update {
                it.copy(
                    isInitial = false,
                    tempPinCode = it.currentPinCode,
                    currentPinCode = "",
                    isValid = false
                )
            }
            viewModelScope.launch {
                _sideEffect.emit(PinCodeSideEffect.ResetPinCode)
            }
        } else {
            verifyPinCode()
        }
    }

    private fun verifyPinCode() {
        val initialPinCode = currentState.tempPinCode
        val confirmPinCode = currentState.currentPinCode
        if (initialPinCode == confirmPinCode) {
            viewModelScope.launch {
                repository.installPinCode(pinCode = currentState.currentPinCode)
                processNavigation()
            }
        } else {
            _pinCodeScreenState.update {
                it.copy(
                    isError = true,
                    isValid = false,
                )
            }
        }
    }

    private suspend fun processNavigation() {
        _sideEffect.emit(PinCodeSideEffect.Save)
    }

    private fun skip() {
        viewModelScope.launch {
            repository.skipPinCode()
            _sideEffect.emit(PinCodeSideEffect.Skip)
        }
    }

    private fun navigateBack() {
            resetToInitialState()
    }

    private fun resetToInitialState() {
        _pinCodeScreenState.update {
            PinCodeScreenState()
        }
        viewModelScope.launch {
            _sideEffect.emit(PinCodeSideEffect.ResetPinCode)
        }
    }

    private companion object {
        const val PIN_CODE_VALID_LENGTH = 4
    }
}