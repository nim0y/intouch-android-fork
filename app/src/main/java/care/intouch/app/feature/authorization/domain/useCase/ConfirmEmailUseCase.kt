package care.intouch.app.feature.authorization.domain.useCase

import app.cashadvisor.common.utill.extensions.logDebugMessage
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface ConfirmEmailUseCase {
    suspend operator fun invoke(id: Int, token: String): Resource<String, ErrorEntity>
    class Base @Inject constructor(
        private val authenticationRepository: AuthenticationRepository,
    ) : ConfirmEmailUseCase {
        override suspend fun invoke(
            id: Int,
            token: String
        ): Resource<String, ErrorEntity> {
            return when (val result = authenticationRepository.confirmEmail(id, token)) {
                is Resource.Success -> {

                    logDebugMessage("accessToken= ${result.data.accessToken}")
                    logDebugMessage("refreshToken= ${result.data.refreshToken}")

                    Resource.Success(result.data.message)
                }

                is Resource.Error -> {
                    Resource.Error(result.error)
                }
            }
        }
    }

}