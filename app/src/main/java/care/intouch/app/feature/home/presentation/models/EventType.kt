package care.intouch.app.feature.home.presentation.models

sealed interface EventType {
    data class ClearTask(val taskId: Int, val index: Int) : EventType
    data class ShareTask(val taskId: Int, val index: Int, val isShared: Boolean) : EventType
    data class ShareDiaryEntry(val entryId: Int, val index: Int, val isShared: Boolean) : EventType
    data class DeleteDiaryEntry(val entryId: Int, val index: Int) : EventType
    data class DuplicateTask(val taskId: Int, val index: Int) : EventType
}