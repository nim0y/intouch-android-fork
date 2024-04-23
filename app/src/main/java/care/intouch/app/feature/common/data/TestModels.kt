package care.intouch.app.feature.common.data

import app.cashadvisor.common.utill.extensions.logDebugMessage


class AccountLocalDataSource {

    fun getAccountInformation(): AccountModel? {
        return AccountModel()
    }

    fun saveAccountInformation(accountModel: AccountModel) {
        logDebugMessage("new accessToken: ${accountModel.accessToken}")
        logDebugMessage("new refreshToken: ${accountModel.refreshToken}")
    }

    fun clearAccountInformation() = Unit
}

data class AccountModel(
    val accessToken: String = "access_token",
    val refreshToken: String = "refresh_token"
)