package care.intouch.app.feature.home.data.mappers

import care.intouch.app.feature.home.data.models.AssignmentsResponse
import care.intouch.app.feature.home.data.models.DiaryNotesResponse
import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Mood
import care.intouch.app.feature.home.domain.models.Status
import care.intouch.app.feature.home.domain.models.Task
import javax.inject.Inject

class HomeMapper @Inject constructor() {
    fun mapAssignments(response: AssignmentsResponse): List<Task> {
        return response.assignments.map { assignment ->
            Task(
                id = assignment.id,
                description = assignment.text,
                isSharedWithDoctor = changeToBoolean(assignment.share),
                status = Status.valueOf(assignment.status)
            )
        }
    }

    fun mapDiaryEntries(response: DiaryNotesResponse): List<DiaryEntry> {
        val diaryEntries = response.diariesNotes.subList(0, 6).map { diaryNote ->
            DiaryEntry(
                id = diaryNote.id,
                data = diaryNote.addDate,
                note = diaryNote.eventDetails,
                moodList = diaryNote.clarifyingEmotion.map {
                    Mood.valueOf(it.name)
                },
                isSharedWithDoctor = diaryNote.visible
            )
        }
        return listOf()
    }

    fun mapAssignmentsRequest(userId: Int, limit: Int = 0, page: Int = 0): HashMap<String, String> {
        val request = hashMapOf("user" to "$userId")
        if (limit != 0) {
            request["limit"] = "$limit"
        }
        if (page != 0) {
            request["page"] = "$page"
        }
        return request
    }

    fun mapDiaryNoteRequest(userId: Int, limit: Int = 0, page: Int = 0): HashMap<String, String> {
        val request = hashMapOf("user" to "$userId")
        if (limit != 0) {
            request["limit"] = "$limit"
        }
        if (page != 0) {
            request["page"] = "$page"
        }
        return request
    }

    private fun changeToBoolean(number: Int): Boolean {
        return number == 1
    }


}