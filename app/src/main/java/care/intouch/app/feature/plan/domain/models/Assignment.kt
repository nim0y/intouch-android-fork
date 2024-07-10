package care.intouch.app.feature.plan.domain.models

import care.intouch.uikit.common.ImageVO

data class Assignment(
    val id: Int,
    val title: String,
    val text: String,
    val date: String,
    val status: AssignmentStatus,
    val imageUrl: String
)
