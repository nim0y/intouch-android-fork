package care.intouch.app.feature.home.data.mappers

import care.intouch.app.feature.home.data.models.Response
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.Task

class HomeMapper {
    fun mapAssignments(response: Response.Assignments): List<Task> {
        return listOf()
    }

    fun mapDiaryEntries(response: Response.DiariesNotes): List<DiaryEntry> {
        return listOf()
    }

}