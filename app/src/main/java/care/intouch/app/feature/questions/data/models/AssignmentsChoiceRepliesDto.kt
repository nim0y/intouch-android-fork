package care.intouch.app.feature.questions.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AssignmentsChoiceRepliesDto(
    val id: Int,
    val reply: String,
    val checked: Boolean
)