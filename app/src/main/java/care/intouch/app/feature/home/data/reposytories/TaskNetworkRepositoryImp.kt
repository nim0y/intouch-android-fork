package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.data.api.AssignmentsApi
import care.intouch.app.feature.home.data.mappers.HomeExceptionMapper
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.TaskNetworkRepository
import care.intouch.app.feature.home.domain.models.Task
import javax.inject.Inject

class TaskNetworkRepositoryImp @Inject constructor(
    private val homeApi: AssignmentsApi,
    private val mapper: HomeMapper,
    private val exceptionMapper: HomeExceptionMapper
) :
    TaskNetworkRepository {
    override suspend fun getTasks(userId: Int): Resource<List<Task>, ErrorEntity> {
        try {
            val request =
                mapper.mapAssignmentsRequest(
                    userId = userId
                )
            val response = homeApi.getClientsAssignments(
                queryParameters = request
            )
            return Resource.Success(mapper.mapAssignments(response))

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun clearAssignment(taskId: Int): Resource<String, ErrorEntity> {
        try {
            val response = homeApi.clearAssignment(assignmentId = taskId)
            return Resource.Success(response.massage)

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun setAssignmentVisible(taskId: Int): Resource<String, ErrorEntity> {
        try {
            val response = homeApi.setAssignmentVisible(assignmentId = taskId)
            return Resource.Success(response.massage)

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun duplicateAssignment(taskId: Int): Resource<String, ErrorEntity> {
        TODO("Not yet implemented")
    }

}