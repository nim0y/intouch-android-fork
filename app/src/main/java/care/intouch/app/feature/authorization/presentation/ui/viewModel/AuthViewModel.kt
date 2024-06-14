package care.intouch.app.feature.authorization.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateUC
import care.intouch.app.feature.authorization.domain.useCase.GetTokenAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModule @Inject constructor(
    private val getTokenAuthentication: GetTokenAuthentication,
    private val getAccountStateUC: GetAccountStateUC,
) : ViewModel() {
    fun getTokenAuth(username: String, password: String) {
        viewModelScope.launch {
            getTokenAuthentication.invoke(username, password)
            getAccountStateUC()
        }
    }
}