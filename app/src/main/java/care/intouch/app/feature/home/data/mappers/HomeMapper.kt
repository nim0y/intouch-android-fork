package care.intouch.app.feature.home.data.mappers

import care.intouch.app.feature.home.data.models.AssignmentsResponse
import care.intouch.app.feature.home.data.models.DiaryNotesResponse
import care.intouch.app.feature.home.data.models.StatusDto
import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Mood
import care.intouch.app.feature.home.domain.models.Status
import care.intouch.app.feature.home.domain.models.Task
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class HomeMapper @Inject constructor() {
    fun mapAssignments(response: AssignmentsResponse): List<Task> {
        val mappedResponse = response.assignments.map { assignment ->
            Task(
                id = assignment.id,
                description = assignment.text,
                isSharedWithDoctor = changeToBoolean(assignment.share),
                status = mapStatus(assignment.status)
            )
        }
        Timber.tag("mappedResponse").d(mappedResponse.toString())
        val mappedResponseList = mutableListOf<Task>()
        val onlyToDo =
            mappedResponse.filter { it.status == Status.TO_DO }.take(MAX_COUNT_OF_TASK_BY_STATUS)
        val onlyInProgress = mappedResponse.filter { it.status == Status.IN_PROGRESS }
            .take(MAX_COUNT_OF_TASK_BY_STATUS)
        Timber.tag("onlyInProgress").d(onlyInProgress.toString())
        Timber.tag("onlyToDo").d(onlyToDo.toString())

        mappedResponseList.addAll(onlyToDo)
        Timber.tag("mappedResponseList1").d(mappedResponseList.toString())
        mappedResponseList.addAll(onlyInProgress)
        Timber.tag("mappedResponseList2").d(mappedResponseList.toString())

        return mappedResponseList.toList()
    }

    fun mapDiaryEntries(response: DiaryNotesResponse): List<DiaryEntry> {
        val diaryEntries = response.diariesNotes.take(MAX_COUNT_OF_DIARY_NOTES).map { diaryNote ->
            DiaryEntry(
                id = diaryNote.id,
                date = formatDate(diaryNote.addDate),
                note = diaryNote.eventDetails,
                moodList = diaryNote.clarifyingEmotion.map {
                    Mood.valueOf(it.name)
                },
                isSharedWithDoctor = diaryNote.visible
            )
        }
        Timber.tag("diaryEntries").d(diaryEntries.toString())
        return diaryEntries
    }

    fun mapAssignmentsRequest(
        userId: Int, limit: Int? = null,
        page: Int? = null
    ): HashMap<String, String> {
        val request = hashMapOf("user" to "$userId")
        if (limit != 0) {
            request["limit"] = "$limit"
        }
        if (page != 0) {
            request["page"] = "$page"
        }
        return request
    }

    fun mapDiaryNoteRequest(
        userId: Int,
        limit: Int? = null,
        page: Int? = null
    ): HashMap<String, String> {
        val request = hashMapOf("user" to "$userId")
        if (limit != null) {
            request["limit"] = "$limit"
        }
        if (page != null) {
            request["page"] = "$page"
        }
        return request
    }

    private fun mapStatus(status: String): Status {
        Timber.tag("status").d(status)
        val statusResult = when (status) {
            StatusDto.TO_DO.status-> Status.TO_DO
            StatusDto.IN_PROGRESS.status -> Status.IN_PROGRESS
            StatusDto.DONE.status -> Status.DONE
            else -> Status.TO_DO
        }

        return statusResult
    }

    private fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        return inputFormat.parse(date)
            ?.let {
                outputFormat.format(it)
                    .lowercase(Locale.ENGLISH)
                    .filterNot { char -> char == '.' }
            } ?: ""
    }

    private fun changeToBoolean(number: Int): Boolean {
        return number == 1
    }

    companion object {
        const val MAX_COUNT_OF_TASK_BY_STATUS = 3
        const val MAX_COUNT_OF_DIARY_NOTES = 6

    }

}