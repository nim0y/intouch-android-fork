package care.intouch.app.feature.home.domain.use_case

import javax.inject.Inject

interface TasksInteractor {
    fun clearTask(id: Int)
    fun shareTaskWithDoctor(id: Int)

    class Base @Inject constructor() : TasksInteractor {
        override fun clearTask(id: Int) {
            TODO("Not yet implemented")
        }

        override fun shareTaskWithDoctor(id: Int) {
            TODO("Not yet implemented")
        }
    }
}