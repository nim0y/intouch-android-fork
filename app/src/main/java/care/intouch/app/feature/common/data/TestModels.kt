package care.intouch.app.feature.common.data

import app.cashadvisor.common.utill.extensions.logDebugMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


//Этот файл будет удален перед мерджем

class AccountLocalDataSource @Inject constructor() {

    private val state: MutableStateFlow<AccountModel?> = MutableStateFlow(AccountModel())

    fun getAccountInformation(): AccountModel? {
        return state.value
    }

    fun saveAccountInformation(accountModel: AccountModel) {
        logDebugMessage("new accessToken: ${accountModel.accessToken}")
        logDebugMessage("new refreshToken: ${accountModel.refreshToken}")
        state.update { accountModel }
    }

    fun clearAccountInformation() {
        state.update { null }
        logDebugMessage("clearAccountInformation")
    }
}

//Сюда нужно добавить токены, если захотите протестить код

data class AccountModel(
    val accessToken: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzEzOTY3OTU3LCJpYXQiOjE3MTM5NjY2NDQsImp0aSI6IjZmOTMwNmJhNzgyZDRlNjFhYTg5OTY3YTAzMGYxMTQzIiwidXNlcl9pZCI6MTIzfQ.UDzQlmVRiI4WRE_m-5Z5QJL6--Qn-cfBClyeqVhj2hE",
    val refreshToken: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTcxNDA1NDA1NywiaWF0IjoxNzEzOTY3NjU3LCJqdGkiOiJkOTI3YjdmM2UxZGU0YWZlYTc2M2YxZDI5MGVmMjExYSIsInVzZXJfaWQiOjEyM30._FbvGInK--uwgXvQJ_ZFrVKSJLiZXWPofIL4fykoatg"
)