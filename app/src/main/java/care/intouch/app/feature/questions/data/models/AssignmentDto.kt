package care.intouch.app.feature.questions.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentDto(
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
    val blocks: List<AssignmentBlockDto>,
    val author: Int,
    @SerialName("author_name")
    val authorName: String,
    @SerialName("is_public")
    val isPublic: Boolean,
    @SerialName("is_favorite")
    val isFavorite: Boolean,
    @SerialName("average_grade")
    val averageGrade: Float
)