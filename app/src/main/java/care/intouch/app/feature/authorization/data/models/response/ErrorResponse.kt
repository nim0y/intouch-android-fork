package care.intouch.app.feature.authorization.data.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String? = null,
    val detail: String? = null,
)