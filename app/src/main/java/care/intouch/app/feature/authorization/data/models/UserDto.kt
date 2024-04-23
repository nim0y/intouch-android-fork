package care.intouch.app.feature.authorization.data.models

data class UserDto(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val acceptPolicy: Boolean? = null,
    val newEmailChanging: Boolean? = null,
    val newEmailTemp: String? = null
)