package care.intouch.app.feature.home.domain

import care.intouch.app.feature.home.domain.models.Task

interface AssignmentRepository {
    suspend fun getTasks(userId: Int): Result<List<Task>>
    suspend fun clearAssignment(taskId: Int): Result<String>
    suspend fun setAssignmentVisible(taskId: Int): Result<String>
    suspend fun duplicateAssignment(taskId: Int): Result<String>
}