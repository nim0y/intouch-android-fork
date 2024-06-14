package care.intouch.app.feature.home.presentation

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _homeState = MutableStateFlow(HomeUiState.Empty)
    val homeState : StateFlow<HomeUiState> = _homeState

    init {

    }

    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.DuplicateTask -> duplicateTask()
            is EventType.DeleteTask -> deleteTask(event.taskId)
            is EventType.ClearTask -> clearTask(event.taskId)
            is EventType.ShearTask -> shearTask(event.taskId)
        }
    }

    private fun deleteTask(taskId: Int) {

    }

    private fun clearTask(taskId: Int) {

    }

    private fun shearTask(taskId: Int) {

    }

    private fun deleteDiaryEntry(diaryId: Int) {

    }

    private fun duplicateTask() {
        // create an absolute empty task, no mette if the prototype was field
    }

}