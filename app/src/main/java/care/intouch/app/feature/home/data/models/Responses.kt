package care.intouch.app.feature.home.data.models

sealed class Responses {
    data class Assignments(val assignments: List<AssignmentDto>) : Responses()
    data class DiariesNotes(val diariesNotes: List<DiaryNoteDto>) : Responses()
    data class Regular(val massage: String) : Responses()
}