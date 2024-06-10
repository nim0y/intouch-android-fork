package care.intouch.app.feature.pinCode.ui.install

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.pinCode.data.PinCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeInstallationViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {
    fun skip() {
        viewModelScope.launch { repository.skipPinCode() }
    }
}