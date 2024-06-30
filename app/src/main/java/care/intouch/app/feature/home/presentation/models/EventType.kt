package care.intouch.app.feature.home.presentation.models

sealed interface EventType {
    data class DeleteTask(val taskId: Int, val index: Int) : EventType
    data class ClearTask(val taskId: Int, val index: Int) : EventType
    data class ShearTask(val taskId: Int, val index: Int, val isShared: Boolean) : EventType
    data class ShearDiaryEntry(val entryId: Int, val index: Int, val isShared: Boolean) : EventType
    data class DeleteDiaryEntry(val entryId: Int, val index: Int) : EventType
    data object DuplicateTask : EventType
}