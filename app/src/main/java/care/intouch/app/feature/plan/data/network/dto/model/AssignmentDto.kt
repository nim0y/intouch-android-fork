package care.intouch.app.feature.plan.data.network.dto.model

import care.intouch.app.feature.plan.domain.models.Assignment
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentDto(
    val id: Int,
    val title: String,
    val text: String,
    @SerialName("update_date")
    val date: String,
    val status: String,
    @SerialName("image_url")
    val imageUrl: String
)

fun AssignmentDto.mapToAssignment(): Assignment {
    val assignmentStatus = when(this.status) {
        "to do" -> AssignmentStatus.TO_DO
        "in progress" -> AssignmentStatus.IN_PROGRESS
        "done" -> AssignmentStatus.DONE
        else -> AssignmentStatus.TO_DO
    }

    return Assignment(
        id = this.id,
        title = this.title,
        text = this.text,
        date = this.date,
        status = assignmentStatus,
        imageUrl = this.imageUrl
    )
}