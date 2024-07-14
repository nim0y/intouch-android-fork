package care.intouch.app.feature.questions.domain.models

data class AssignmentsBlock(
    val id: Int,
    val choiceReplies: List<AssignmentsChoiceReplies>?,
    val leftPole: String,
    val rightPole: String,
    val image: String?,
    val question: String,
    val reply: String,
    val description: String,
    val type: String,
    val startRange: Int,
    val endRange: Int,
)
