package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.SerialName

class GetUserResponse(
    val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") var lastName: String,
    @SerialName("email") var email: String,
)