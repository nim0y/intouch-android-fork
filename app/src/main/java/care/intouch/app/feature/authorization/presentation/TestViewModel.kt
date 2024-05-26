package care.intouch.app.feature.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.data.dto.AccountState
import care.intouch.app.feature.authorization.domain.useCase.ConfirmEmailUseCase
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateFlowUC
import care.intouch.app.feature.authorization.domain.useCase.GetUserNameUseCase
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.utill.extensions.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Этот файл будет удален перед мерджем
@HiltViewModel
class TestViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCase,
    private val getAccountStateFlowUC: GetAccountStateFlowUC,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getAccountStateFlowUC().collect { state ->
                when (state) {
                    is AccountState.Account -> {
                        Logger.realLogger?.d("account state = AccountState.Account")
                        Logger.realLogger?.d("user id = ${state.userId}")
                        Logger.realLogger?.d("user access token  = ${state.accessToken}")
                        Logger.realLogger?.d("user refresh token  = ${state.refreshToken}")
                    }

                    AccountState.NoAccount -> {
                        Logger.realLogger?.d("account state = AccountState.NoAccount")
                    }
                }
            }
        }
    }
    fun confirmEmail() {
        viewModelScope.launch {
            when (val data =
                confirmEmailUseCase(id = 163, token = "c7n3wy-23237c3d2d536783d63306d2dcc23807")) {
                is Resource.Error -> Logger.realLogger?.d("confirmEmailUseCase error =  ${data.error}")
                is Resource.Success -> Logger.realLogger?.d("confirmEmailUseCase success = ${data.data}")
            }
            when (val userName = getUserNameUseCase()) {
                is Resource.Error -> Logger.realLogger?.d("getUserNameUseCase error =  ${userName.error}")
                is Resource.Success -> Logger.realLogger?.d("getUserNameUseCase success = ${userName.data}")
            }
        }
    }
}