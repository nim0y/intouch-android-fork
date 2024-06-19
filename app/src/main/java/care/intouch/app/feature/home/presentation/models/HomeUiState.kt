package care.intouch.app.feature.home.presentation.models

sealed class HomeUiState {
    data object Empty : HomeUiState()
    data class FilledScreen(val taskList: ArrayList<Task>, val diaryList: ArrayList<DiaryEntry>) :
        HomeUiState()
}