package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.home.domain.AssignmentNetworkRepository
import care.intouch.app.feature.home.domain.models.Task
import javax.inject.Inject

interface GetTasks {
    suspend fun getTasks(userId: Int): Result<List<Task>>

    class Base @Inject constructor(private val repository: AssignmentNetworkRepository) : GetTasks {
        override suspend fun getTasks(userId: Int): Result<List<Task>> {
            return repository.getTasks(userId = userId)
        }
    }
}