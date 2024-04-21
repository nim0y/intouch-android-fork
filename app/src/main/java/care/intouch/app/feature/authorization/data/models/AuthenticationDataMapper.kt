package care.intouch.app.feature.authorization.data.models

import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse

class AuthenticationDataMapper {
    fun toConfirmEmailDto(confirmEmailResponse: ConfirmEmailResponse): ConfirmEmailDto {
        return ConfirmEmailDto(
            accessToken = confirmEmailResponse.accessToken,
            refreshToken = confirmEmailResponse.refreshToken
        )
    }
}