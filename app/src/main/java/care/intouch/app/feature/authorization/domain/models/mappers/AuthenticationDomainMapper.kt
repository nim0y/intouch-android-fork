package care.intouch.app.feature.authorization.domain.models.mappers

import care.intouch.app.feature.authorization.data.models.AuthenticationOutputDto
import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.domain.models.AuthenticationOutputData
import javax.inject.Inject

class AuthenticationDomainMapper @Inject constructor() {
    fun toAuthenticationOutputData(authenticationOutputDto: AuthenticationOutputDto): AuthenticationOutputData {
        if (authenticationOutputDto.accessToken.isNullOrBlank() || authenticationOutputDto.refreshToken.isNullOrBlank()) {
            when {
                authenticationOutputDto.message != null -> {
                    throw AuthenticationException.Authentication.AlreadyActivated(
                        message = authenticationOutputDto.message
                    )
                }

                authenticationOutputDto.error != null -> {
                    throw AuthenticationException.Authentication.InvalidToken(
                        message = authenticationOutputDto.error
                    )
                }

                else -> {
                    throw AuthenticationException.Undefined(
                        message = SOMETHING_WENT_WRONG
                    )
                }
            }
        } else {
            return AuthenticationOutputData(
                accessToken = authenticationOutputDto.accessToken,
                refreshToken = authenticationOutputDto.refreshToken,
                message = authenticationOutputDto.message ?: BLANC_STRING
            )
        }
    }

    companion object {
        const val BLANC_STRING = ""
        const val SOMETHING_WENT_WRONG = "Something went wrong"
    }
}