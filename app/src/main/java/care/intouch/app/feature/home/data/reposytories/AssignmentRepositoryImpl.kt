package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.home.data.api.AssignmentsApi
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.AssignmentNetworkRepository
import care.intouch.app.feature.home.domain.models.Task
import javax.inject.Inject

class AssignmentRepositoryImpl @Inject constructor(
    private val homeApi: AssignmentsApi,
    private val mapper: HomeMapper
) : AssignmentNetworkRepository {
    override suspend fun getTasks(userId: Int): Result<List<Task>> {
        try {
            val request = mapper.mapAssignmentsRequest(userId = userId)
            val response = homeApi.getClientsAssignments(queryParameters = request)
            return Result.success(mapper.mapAssignments(response))
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun clearAssignment(assignmentId: Int): Result<String> {
        try {
            val response = homeApi.clearAssignment(assignmentId = assignmentId)
            return Result.success(response.massage)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun setAssignmentVisible(assignmentId: Int): Result<String> {
        try {
            val response = homeApi.setAssignmentVisible(assignmentId = assignmentId)
            return Result.success(response.massage)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun duplicateAssignment(taskId: Int): Result<String> {
        TODO("Not yet implemented")
    }
}