package care.intouch.app.feature.home.presentation

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Mood
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
            diaryList = arrayListOf(
                DiaryEntry(
                    id = 1,
                    data = "13, jul",
                    note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                    moodList = listOf(Mood(name = "Bad")),
                    sharedWithDoc = false
                ),
                DiaryEntry(
                    id = 1,
                    data = "13, jul",
                    note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                    moodList = listOf(Mood(name = "Bad")),
                    sharedWithDoc = false
                )
            )
        )
    }

    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.DuplicateTask -> duplicateTask()
            is EventType.DeleteTask -> deleteTask(event.taskId, event.index)
            is EventType.ClearTask -> clearTask(event.taskId, event.index)
            is EventType.ShearTask -> shearTask(event.taskId, event.index, event.isShared)
            is EventType.ShearDiaryEntry -> shearEntry(event.entryId, event.index, event.isShared)
            is EventType.DeleteDiaryEntry-> deleteDiaryEntry(event.entryId, event.index)
        }
    }

    private fun deleteTask(taskId: Int, index: Int) {
        val newState = _homeState.value
        if (newState is HomeUiState.FilledScreen) {
        newState.taskList.removeAt(index)
        }
        _homeState.value = newState
    }

    private fun clearTask(taskId: Int, index: Int) {
        val newState = _homeState.value
        if (newState is HomeUiState.FilledScreen) {
            val clearedTask = newState.taskList[index].copy(
                status = Status.TO_DO,
                sharedWithDoc = false,
                description = ""
            )
            newState.taskList.removeAt(index)
            newState.taskList.add(index = 0, clearedTask)
        }
        _homeState.value = newState
    }

    private fun shearTask(taskId: Int, index: Int, shareStatus: Boolean) {
        val newState = _homeState.value
        if (newState is HomeUiState.FilledScreen) {
            val task = newState.taskList[index].copy(sharedWithDoc = shareStatus)
            newState.taskList[index] = task
        }

        _homeState.value = newState

    }

    private fun shearEntry(entryId: Int, index: Int, shareStatus: Boolean) {
        val newState = _homeState.value
        if (newState is HomeUiState.FilledScreen) {
            val entry = newState.diaryList[index].copy(sharedWithDoc = shareStatus)
            newState.diaryList[index] = entry
        }

    }

    private fun deleteDiaryEntry(diaryId: Int, index: Int) {
        val newState = _homeState.value
        if (newState is HomeUiState.FilledScreen) {
            newState.diaryList.removeAt(index)
        }
        _homeState.value = newState
    }

    private fun duplicateTask() {
        // create an absolute empty task, no mette if the prototype was field
    }

}