package care.intouch.app.feature.authorization.data.models

data class AuthenticationOutputDto(
    val accessToken: String?,
    val refreshToken: String?,
    val message: String?,
    val error: String?
)