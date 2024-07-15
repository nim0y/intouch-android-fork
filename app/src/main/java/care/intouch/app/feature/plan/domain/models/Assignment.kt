package care.intouch.app.feature.plan.domain.models

data class Assignment(
    val title: String,
    val date: String,
    val status: AssignmentStatus
)
