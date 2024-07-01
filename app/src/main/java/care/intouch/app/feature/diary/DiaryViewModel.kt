package care.intouch.app.feature.diary

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.diary.presentation.ui.models.DiaryChangeEvent
import care.intouch.app.feature.diary.presentation.ui.models.DiaryDataState
import care.intouch.app.feature.diary.presentation.ui.models.DiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.DiaryUiState
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import care.intouch.app.feature.diary.presentation.ui.models.ViewsComponentsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor() : ViewModel() {
    private var _diaryState =
        MutableStateFlow(DiaryUiState(loadDiaryData(), ViewsComponentsState()))
    val diaryState = _diaryState.asStateFlow()

    val currentData = loadDiaryData()

    fun onEvent(event: DiaryChangeEvent) {
        when (event) {
            is DiaryChangeEvent.OnShareWithDoc -> onShareToggle(event.idToShare)
            is DiaryChangeEvent.IntentionToDelete -> showDeletePopup(event.idToDelete)
            is DiaryChangeEvent.ConfirmDelete -> confirmDelete()
            DiaryChangeEvent.deleteCancel -> cancelDelete()

        }
    }

    private fun showDeletePopup(idToDelete: Int) {
        _diaryState.value = _diaryState.value.copy(
            diaryDataState = _diaryState.value.diaryDataState.copy(selectedIdToDelete = idToDelete),
            viewsComponentsState = ViewsComponentsState(
                noteListVisibility = false,
                noteDeletePopup = true,
            )
        )
    }

    private fun confirmDelete() {
        val idToDelete = _diaryState.value.diaryDataState.selectedIdToDelete ?: return

        val updatedData = _diaryState.value.diaryDataState.noteList.filter { it.id != idToDelete }
        _diaryState.value = _diaryState.value.copy(
            diaryDataState = DiaryDataState(
                updatedData,
                null
            )
        )
        refreshUi()
    }

    private fun cancelDelete() {
        _diaryState.value = DiaryUiState(
            diaryDataState = currentData,
            viewsComponentsState = ViewsComponentsState(
                noteListVisibility = true,
                noteDeletePopup = false
            )
        )
    }

    private fun onShareToggle(idToShare: Int) {
        val entryIndex =
            _diaryState.value.diaryDataState.noteList.indexOfFirst { it.id == idToShare }
        if (entryIndex != -1) {
            val updatedData = _diaryState.value.diaryDataState.noteList.toMutableList()
            val entryToUpdate = updatedData[entryIndex]
            entryToUpdate.sharedWithDoc = !entryToUpdate.sharedWithDoc
            updatedData[entryIndex] = entryToUpdate
            _diaryState.value = _diaryState.value.copy(diaryDataState = DiaryDataState(updatedData))
        }
        refreshUi()
    }

    private fun refreshUi() {
        _diaryState.value = _diaryState.value.copy(
            viewsComponentsState = ViewsComponentsState(
                noteListVisibility = true,
                noteDeletePopup = false,
            )
        )
    }

    private fun loadDiaryData(): DiaryDataState {
        val entries = generateRandomDiaryEntries(3)
        return DiaryDataState(entries)
    }

    private fun generateRandomDiaryEntries(count: Int): List<DiaryEntry> {
        val random = Random()
        val moods = listOf(
            Mood(name = "bad"),
            Mood(name = "Sad"),
            Mood(name = "Angry"),
            Mood(name = "Excited")
        )
        val notes =
            listOf("Went for a walk", "Had a great meal", "Felt stressed", "Met with friends")
        val month = listOf("jan", "mar", "oct", "nov")

        return List(count) {
            DiaryEntry(
                id = random.nextInt(100),
                data = "${random.nextInt(31)} ${month[random.nextInt(month.size)]}",
                note = notes[random.nextInt(notes.size)],
                moodList = moods,
                sharedWithDoc = random.nextBoolean(),
            )
        }
    }
}