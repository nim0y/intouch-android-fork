package care.intouch.app.account.data.dto.impl

import android.util.Log
import care.intouch.app.account.data.dto.AccountModel
import care.intouch.app.account.data.dto.AccountState
import care.intouch.app.account.domain.api.AccountLocalDataSource
import care.intouch.app.account.domain.api.AccountStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountStateRepositoryImpl @Inject constructor(
    private val localDataSourceImpl: AccountLocalDataSource,
) :
    AccountStateRepository {
    override suspend fun clearAccount() {
        Log.d("1", "clear")
        localDataSourceImpl.clearAccountInformation()
    }

    override suspend fun createAccount(userId: Int, accessToken: String, refreshToken: String) {
        localDataSourceImpl.saveAccountInformation(
            AccountModel(userId = userId, accessToken = accessToken, refreshToken = refreshToken)
        )
        Log.d("2", "userId = $userId,accessToken = $accessToken")
    }

    override suspend fun getAccountState(): Flow<AccountState?> {
        localDataSourceImpl.getAccountFlow().map {
            if (it == null) {
                Log.d("3", "NoAccount")
            } else {
                Log.d("3", "HasAccount")
            }
        }
        return localDataSourceImpl.getAccountFlow().map {
            if (it == null) {
                AccountState.NoAccount
            } else {
                AccountState.Account(
                    userId = it.userId,
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken
                )
            }
        }
    }

    override suspend fun getAccountInformation(): AccountState {
        val account = localDataSourceImpl.getAccountInformation()
        if (account == null) {
            Log.d("4", "NoAccount")
        } else {
            Log.d("4", "HasAccount")
        }
        return if (account == null) {
            AccountState.NoAccount
        } else {
            AccountState.Account(
                userId = account.userId,
                accessToken = account.accessToken,
                refreshToken = account.refreshToken
            )
        }
    }
}