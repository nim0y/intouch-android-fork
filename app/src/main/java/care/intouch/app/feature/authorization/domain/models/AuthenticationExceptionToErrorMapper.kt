package care.intouch.app.feature.authorization.domain.models

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import care.intouch.app.feature.common.domain.models.ErrorEntity
import javax.inject.Inject

class AuthenticationExceptionToErrorMapper @Inject constructor() : BaseExceptionToErrorMapper() {
    override fun handleSpecificException(exception: Exception): ErrorEntity {
        return when (exception) {
            is AuthenticationException.Authentication.AlreadyActivated -> {
                ErrorEntity.Authentication.AlreadyActivated(exception.message)
            }

            is AuthenticationException.Authentication.InvalidToken -> {
                ErrorEntity.Authentication.InvalidToken(exception.message)
            }

            is AuthenticationException.Authentication.NotFound -> {
                ErrorEntity.Authentication.UserNotExist(exception.message)
            }

            is AuthenticationException.NoInternetConnection -> {
                ErrorEntity.NetworksError.NoInternetConnection(exception.message)
            }

            else -> {
                ErrorEntity.UnknownError(exception.message ?: BLANC_STRING)
            }
        }

    }

    companion object {
        private const val BLANC_STRING = ""
    }

}
