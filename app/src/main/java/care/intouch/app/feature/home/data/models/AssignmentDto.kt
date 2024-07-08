package care.intouch.app.feature.home.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AssignmentDto(
    val id: Int,
    val title: String,
    val text: String,
    val updateDate: String,
    val addDate: String,
    val assignmentType: String,
    val status: String,
    val share: Int,
    val user: Int,
    val visible: Boolean,
    val grade: Int,
    val review: String,
    val assignmentRoot: Int
)