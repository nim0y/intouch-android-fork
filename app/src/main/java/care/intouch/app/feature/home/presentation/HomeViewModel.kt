package care.intouch.app.feature.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Mood
import care.intouch.app.feature.home.presentation.models.Status
import care.intouch.app.feature.home.presentation.models.Task
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private var homeState = MutableStateFlow(HomeUiState())
    private val taskList = mutableStateListOf<Task>()
    private val diaryList = mutableStateListOf<DiaryEntry>()
    private var dialogEventJob: Job? = null

    init {
        homeState.value = HomeUiState(
            taskList = mutableStateListOf(
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
            diaryList = mutableStateListOf(
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
        taskList.addAll(homeState.value.taskList)
        diaryList.addAll(homeState.value.diaryList)
    }

    fun getState(): StateFlow<HomeUiState> = homeState

    private fun getCurrentStatus(): HomeUiState = homeState.value
    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.DuplicateTask -> duplicateTask(event.taskId, event.index)
            is EventType.ClearTask -> clearTask(event.taskId, event.index)
            is EventType.ShearTask -> shearTask(event.taskId, event.index, event.isShared)
            is EventType.ShearDiaryEntry -> shearEntry(event.entryId, event.index, event.isShared)
            is EventType.DeleteDiaryEntry -> deleteDiaryEntry(event.entryId, event.index)
            is EventType.OpenCloseDeleteEntryDialog -> openCloseDeleteEntryDialog()
            is EventType.OpenCloseClearTaskDialog -> openCloseClearTaskDialog()
            is EventType.CancelJob -> cancelJob()
        }
    }

    private fun duplicateTask(taskId: Int, index: Int) {
        val newItem = taskList[index]
        taskList.add(newItem)
        homeState.value = getCurrentStatus().copy(taskList = taskList)
    }

    private fun clearTask(taskId: Int, index: Int) {
        dialogEventJob = viewModelScope.launch {
            while (true) {
                if (!homeState.value.clearTaskDialogState) {
                    val clearedTask = taskList[index].copy(
                        status = Status.TO_DO,
                        sharedWithDoc = false,
                        description = ""
                    )
                    taskList.removeAt(index)
                    taskList.add(index = 0, clearedTask)
                    homeState.value = getCurrentStatus().copy(taskList = taskList)
                    break
                }
                delay(100L)

            }
        }

    }

    private fun shearTask(taskId: Int, index: Int, shareStatus: Boolean) {
        taskList[index].sharedWithDoc = shareStatus
        homeState.value = getCurrentStatus().copy(taskList = taskList)

    }

    private fun shearEntry(entryId: Int, index: Int, shareStatus: Boolean) {
        diaryList[index].sharedWithDoc = shareStatus
        homeState.value = getCurrentStatus().copy(diaryList = diaryList)
    }

    private fun deleteDiaryEntry(diaryId: Int, index: Int) {
        dialogEventJob = viewModelScope.launch {
            while (true) {
                if (!homeState.value.deleteDiaryEntryDialogState) {
                    diaryList.removeAt(index)
                    homeState.value = getCurrentStatus().copy(diaryList = diaryList)
                    break
                }
                delay(100L)
            }
        }
    }

    private fun cancelJob() {
        dialogEventJob?.cancel()
    }

    private fun openCloseDeleteEntryDialog() {
        homeState.value =
            getCurrentStatus().copy(deleteDiaryEntryDialogState = !homeState.value.deleteDiaryEntryDialogState)
    }

    private fun openCloseClearTaskDialog() {
        homeState.value =
            getCurrentStatus().copy(clearTaskDialogState = !homeState.value.clearTaskDialogState)
    }
}


