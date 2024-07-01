package care.intouch.app.feature.home.presentation.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class HomeUiState(
    val taskList: SnapshotStateList<Task> = mutableStateListOf(),
    val diaryList: SnapshotStateList<DiaryEntry> = mutableStateListOf(),
    val isSeeAllPlanVisible: Boolean = taskList.isNotEmpty(),
    val isDiaryListEmpty: Boolean = diaryList.isNotEmpty(),
    val deleteDiaryEntryDialogState: Boolean=false,
    val clearTaskDialogState: Boolean=false
)