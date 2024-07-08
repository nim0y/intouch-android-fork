package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.home.presentation.models.Task
import javax.inject.Inject

interface GetTasks {

    suspend fun getTasks(): Resource<List<Task>, ErrorEntity>

    class Base @Inject constructor() : GetTasks {
        override suspend fun getTasks(): Resource<List<Task>, ErrorEntity> {
            TODO("Not yet implemented")
        }
    }
}