package care.intouch.app.ui.uiKitSamples.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.ConfirmEmailUseCase
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
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {
    fun confirmEmail() {
        viewModelScope.launch {
            when (val data =
                confirmEmailUseCase(id = 123, token = "c5znpw-120d5e72d862fea9a39bce1f590e1992")) {
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