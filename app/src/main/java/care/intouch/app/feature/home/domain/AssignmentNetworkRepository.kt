package care.intouch.app.feature.home.domain

import care.intouch.app.feature.home.domain.models.Task

interface AssignmentNetworkRepository {
    suspend fun getTasks(userId: Int): Result<List<Task>>
    suspend fun clearAssignment(assignmentId: Int): Result<String>
    suspend fun setAssignmentVisible(assignmentId: Int): Result<String>
    suspend fun duplicateAssignment(taskId: Int): Result<String>
}