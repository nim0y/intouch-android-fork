package care.intouch.app.feature.authorization.data

import care.intouch.app.feature.authorization.data.models.AuthenticationOutputDto
import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse

class AuthenticationDataMapper {
    fun toConfirmEmailDto(confirmEmailResponse: ConfirmEmailResponse): AuthenticationOutputDto {
        return AuthenticationOutputDto(
            accessToken = confirmEmailResponse.accessToken,
            refreshToken = confirmEmailResponse.refreshToken,
            message = confirmEmailResponse.message
        )
    }
}