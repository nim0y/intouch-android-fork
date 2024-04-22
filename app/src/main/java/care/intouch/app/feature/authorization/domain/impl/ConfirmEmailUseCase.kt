package care.intouch.app.feature.authorization.domain.impl

import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.authorization.domain.api.CredentialsRepository
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.models.ErrorEntity

interface ConfirmEmailUseCase {
    suspend operator fun invoke(id: String, token: String): Resource<String, ErrorEntity>
    class Base(
        private val authenticationRepository: AuthenticationRepository,
        private val credentialsRepository: CredentialsRepository
    ) : ConfirmEmailUseCase {
        override suspend fun invoke(
            id: String,
            token: String
        ): Resource<String, ErrorEntity> {
            return when (val result = authenticationRepository.confirmEmail(id, token)) {
                is Resource.Success -> {
                    credentialsRepository.saveCredentials(
                        result.data.accessToken,
                        result.data.refreshToken
                    )
                    Resource.Success(result.data.message)
                }

                is Resource.Error -> {
                    Resource.Error(result.error)
                }
            }
        }
    }

}