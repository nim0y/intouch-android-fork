package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.home.domain.AssignmentRepository
import care.intouch.app.feature.home.domain.models.Task
import timber.log.Timber
import javax.inject.Inject

interface GetTasks {
    suspend fun getTasks(userId: Int): Result<List<Task>>

    class Base @Inject constructor(private val repository: AssignmentRepository) : GetTasks {
        override suspend fun getTasks(userId: Int): Result<List<Task>> {
            val result = repository.getAssignments(userId = userId)
            Timber.tag("resultUseCase").d(result.toString())
            return result
        }
    }
}