package care.intouch.app.feature.authorization.data.models

data class ConfirmEmailDto(
    val accessToken: String,
    val refreshToken: String,
)