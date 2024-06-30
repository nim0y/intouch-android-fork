package care.intouch.app.feature.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Mood
import care.intouch.app.feature.home.presentation.models.Status
import care.intouch.app.feature.home.presentation.models.Task
import javax.inject.Inject

//@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private var homeState = MutableLiveData(HomeUiState())
    private val taskList = mutableStateListOf<Task>()
    private val diaryList = mutableStateListOf<DiaryEntry>()

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
        taskList.addAll(homeState.value!!.taskList)
        diaryList.addAll(homeState.value!!.diaryList)
    }

    fun getState(): LiveData<HomeUiState> = homeState

    private fun getCurrentStatus(): HomeUiState = homeState.value!!
    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.DuplicateTask -> duplicateTask()
            is EventType.ClereTask -> copyTask(event.taskId, event.index)
            is EventType.ClearTask -> clearTask(event.taskId, event.index)
            is EventType.ShearTask -> shearTask(event.taskId, event.index, event.isShared)
            is EventType.ShearDiaryEntry -> shearEntry(event.entryId, event.index, event.isShared)
            is EventType.DeleteDiaryEntry -> deleteDiaryEntry(event.entryId, event.index)
        }
    }

    private fun copyTask(taskId: Int, index: Int) {
        val newItem = taskList[index]
        taskList.add(newItem)
        homeState.value = getCurrentStatus().copy(taskList = taskList)
    }

    private fun clearTask(taskId: Int, index: Int) {
        val clearedTask = taskList[index].copy(
            status = Status.TO_DO,
            sharedWithDoc = false,
            description = ""
        )
        taskList.removeAt(index)
        taskList.add(index = 0, clearedTask)
        homeState.value = getCurrentStatus().copy(taskList = taskList)
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
        diaryList.removeAt(index)
        homeState.value = getCurrentStatus().copy(diaryList = diaryList)
    }

    private fun duplicateTask() {
        // create an absolute empty task, no mette if the prototype was field
    }

}