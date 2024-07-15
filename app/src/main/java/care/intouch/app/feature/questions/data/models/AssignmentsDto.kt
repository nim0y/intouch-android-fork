package care.intouch.app.feature.questions.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentsDto(
    val id: Int,
    val title: String,
    val text: String,
    @SerialName("update_date")
    val updateDate: String,
    @SerialName("add_date")
    val addDate: String,
    @SerialName("assignment_type")
    val assignmentType: String,
    val status: String,
    val tags: String,
    val language: String,
    val share: Int,
    @SerialName("image_url")
    val imageUrl: String,
    val blocks: List<AssignmentsBlockDto>,
    val author: Int,
    @SerialName("author_name")
    val authorName: String,
    val user: Int,
    val visible: Boolean,
    val grade: Int?,
    val review: String?,
    @SerialName("assignment_root")
    val assignmentRoot: Int
)