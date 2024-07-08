package care.intouch.app.feature.home.data.models

sealed class Response {
    data class Assignments(val assignments: List<AssignmentDto>) : Response()
    data class DiariesNotes(val diariesNotes: List<DiaryNoteDto>) : Response()
    data class Regular(val massage: String) : Response()
}