package care.intouch.app.account.data.dto

data class AccountModel(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
)
