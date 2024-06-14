package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface GetTokenAuthentication {
    suspend operator fun invoke(username: String, password: String): Resource<String, ErrorEntity>

    class Base @Inject constructor(
        private val authenticationRepository: AuthenticationRepository,
        private val accountRepository: AccountStateRepository,
        private val userRepository: UserRepository,
    ) : GetTokenAuthentication {
        override suspend fun invoke(
            username: String,
            token: String,
        ): Resource<String, ErrorEntity> {
            return when (
                val result = authenticationRepository.getToken(username, token)) {
                is Resource.Success -> {
                    when (val result2 = userRepository.getUser()) {
                        is Resource.Success -> {
                            accountRepository.createAccount(
                                result2.data.id,
                                result.data.accessToken,
                                result.data.refreshToken
                            )
                        }

                        is Resource.Error -> {
                        }
                    }
                    Resource.Success("Success")
                }

                is Resource.Error -> {
                    Resource.Error(result.error)
                }
            }
        }
    }
}