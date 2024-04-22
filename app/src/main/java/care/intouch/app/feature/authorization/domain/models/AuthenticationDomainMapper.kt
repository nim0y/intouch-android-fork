package care.intouch.app.feature.authorization.domain.models

import care.intouch.app.feature.authorization.data.models.AuthenticationOutputDto
import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException

class AuthenticationDomainMapper {
    fun toAuthenticationOutputData(authenticationOutputDto: AuthenticationOutputDto): AuthenticationOutputData {
        if (authenticationOutputDto.accessToken.isNullOrBlank() && authenticationOutputDto.refreshToken.isNullOrBlank()) {
            throw AuthenticationException.Authentication.AlreadyActivated(
                authenticationOutputDto.message ?: BLANC_STRING
            )
        } else {
            return AuthenticationOutputData(
                accessToken = authenticationOutputDto.accessToken ?: BLANC_STRING,
                refreshToken = authenticationOutputDto.refreshToken ?: BLANC_STRING,
                message = authenticationOutputDto.message ?: BLANC_STRING
            )
        }
    }

    companion object {
        const val BLANC_STRING = ""
    }
}