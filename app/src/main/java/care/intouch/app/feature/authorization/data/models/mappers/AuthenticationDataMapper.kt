package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.AuthenticationOutputDto
import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import javax.inject.Inject

class AuthenticationDataMapper @Inject constructor() {
    fun toConfirmEmailDto(confirmEmailResponse: ConfirmEmailResponse): AuthenticationOutputDto {
        return AuthenticationOutputDto(
            accessToken = confirmEmailResponse.accessToken,
            refreshToken = confirmEmailResponse.refreshToken,
            message = confirmEmailResponse.message,
            error = confirmEmailResponse.error
        )
    }
}