package care.intouch.app.feature.diary.presentation.ui.models

sealed class DiaryChangeEvent {
    object deleteCancel : DiaryChangeEvent()
    data class ConfirmDelete(val idToDelete: Int) : DiaryChangeEvent()
    data class IntentionToDelete(val idToDelete: Int) : DiaryChangeEvent()
    data class OnShareWithDoc(val idToShare: Int) : DiaryChangeEvent()
}