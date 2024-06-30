package care.intouch.app.feature.home.presentation.models

sealed class HomeUiState(
    val isSeeAllPlanVisible: Boolean,
    val isDiaryListEmpty: Boolean
) {
    data object Empty : HomeUiState(
        isSeeAllPlanVisible = false,
        isDiaryListEmpty = false
    )

    data class FilledScreen(
        val taskList: ArrayList<Task>,
        val diaryList: ArrayList<DiaryEntry>,
    ) :
        HomeUiState(
            isSeeAllPlanVisible = taskList.isNotEmpty(),
            isDiaryListEmpty = diaryList.isNotEmpty()
        )
}