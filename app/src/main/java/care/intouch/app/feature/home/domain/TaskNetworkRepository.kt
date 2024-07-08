package care.intouch.app.feature.home.domain

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.presentation.models.Task

interface TaskNetworkRepository {
    suspend fun getTasks(userId: Int): Resource<List<Task>, ErrorEntity>
    suspend fun clearAssignment(taskId: Int)
    suspend fun setAssignmentVisible(taskId: Int)
}