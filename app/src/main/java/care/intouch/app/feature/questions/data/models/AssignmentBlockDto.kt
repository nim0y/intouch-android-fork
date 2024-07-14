package care.intouch.app.feature.questions.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentBlockDto(
    val id: Int,
    @SerialName("choice_replies")
    val choiceReplies: List<AssignmentChoiceRepliesDto>?,
    @SerialName("left_pole")
    val leftPole: String,
    @SerialName("right_pole")
    val rightPole: String,
    val image: String?,
    val question: String,
    val reply: String,
    val description: String,
    val type: String,
    @SerialName("start_range")
    val startRange: Int,
    @SerialName("end_range")
    val endRange: Int,
)