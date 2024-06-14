package care.intouch.app.feature.home.presentation.models

sealed class HomeUiState {
    data object Empty : HomeUiState()
    data class PlansExists(val taskList: ArrayList<Task>) : HomeUiState()
    data class MyDiaryExists(val diaryList: ArrayList<DiaryEntry>) : HomeUiState()
    data class FilledScreen(val taskList: ArrayList<Task>, val diaryList: ArrayList<DiaryEntry>) :
        HomeUiState()
}