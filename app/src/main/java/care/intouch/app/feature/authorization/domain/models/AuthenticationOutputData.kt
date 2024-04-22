package care.intouch.app.feature.authorization.domain.models

data class AuthenticationOutputData(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)