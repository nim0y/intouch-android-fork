package care.intouch.app.feature.home.presentation

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Status
import care.intouch.app.feature.home.presentation.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeState: StateFlow<HomeUiState> = _homeState

    init {
        _homeState.value = HomeUiState.FilledScreen(
            taskList = arrayListOf(
                Task(
                    id = 1,
                    status = Status.TO_DO,
                    sharedWithDoc = false,
                    description = "abobaлфвыадловыалвоадаодылваоыдлваовыдлаоывлаодвыдалоывлаоывдалофывдлаоывдлаоывдлоаывлдаоывдлаоывдлаоывдлаоывфдлоафы"
                ),
                Task(
                    id = 1,
                    status = Status.TO_DO,
                    sharedWithDoc = false,
                    description = "aboba"
                )
            ),
            diaryList = arrayListOf()
        )
    }

    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.DuplicateTask -> duplicateTask()
            is EventType.DeleteTask -> deleteTask(event.taskId)
            is EventType.ClearTask -> clearTask(event.taskId)
            is EventType.ShearTask -> shearTask(event.taskId, event.index, event.isShared)
        }
    }

    private fun deleteTask(taskId: Int) {

    }

    private fun clearTask(taskId: Int) {

    }

    private fun shearTask(taskId: Int, index: Int, shareStatus: Boolean) {
        val newState = _homeState.value
        if (newState is HomeUiState.FilledScreen) {
            val task = newState.taskList[index].copy(sharedWithDoc = shareStatus)
            newState.taskList[index] = task
        }

        _homeState.value = newState

    }

    private fun deleteDiaryEntry(diaryId: Int) {

    }

    private fun duplicateTask() {
        // create an absolute empty task, no mette if the prototype was field
    }

}