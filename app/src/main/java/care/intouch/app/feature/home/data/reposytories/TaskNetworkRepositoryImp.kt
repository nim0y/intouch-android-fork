package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.data.mappers.HomeExceptionMapper
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.data.models.AssignmentsApi
import care.intouch.app.feature.home.data.models.Response
import care.intouch.app.feature.home.domain.TaskNetworkRepository
import care.intouch.app.feature.home.presentation.models.Task
import javax.inject.Inject

class TaskNetworkRepositoryImp @Inject constructor(
    private val homeApi: AssignmentsApi,
    private val mapper: HomeMapper,
    private val exceptionMapper: HomeExceptionMapper
) :
    TaskNetworkRepository {
    override suspend fun getTasks(userId: Int): Resource<List<Task>, ErrorEntity> {
        try {
            val response = homeApi.getClientsAssignments()
            return Resource.Success(mapper.mapAssignments(response))

        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            return Resource.Error(error)
        }
    }

    override suspend fun clearAssignment(taskId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun setAssignmentVisible(taskId: Int) {
        TODO("Not yet implemented")
    }


}