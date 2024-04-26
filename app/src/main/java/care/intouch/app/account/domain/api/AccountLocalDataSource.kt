package care.intouch.app.account.domain.api

import care.intouch.app.account.data.dto.AccountModel
import kotlinx.coroutines.flow.Flow

interface AccountLocalDataSource {
    suspend fun saveAccountInformation(account: AccountModel)
    suspend fun getAccountInformation(): AccountModel?
    fun getAccountFlow(): Flow<AccountModel?>
    suspend fun hasAccount(): Boolean
    suspend fun clearAccountInformation()

}