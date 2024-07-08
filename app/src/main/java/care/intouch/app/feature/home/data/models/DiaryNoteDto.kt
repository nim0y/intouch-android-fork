package care.intouch.app.feature.home.data.models

import kotlinx.serialization.Serializable

@Serializable
data class DiaryNoteDto(
    val id: Int,
    val author: Int,
    val addDate: String,
    val visible: Boolean,
    val eventDetails: String,
    val eventDetailsTags: String,
    val thoughtsAnalysis: String,
    val thoughtsAnalysisTags: String,
    val emotionType: String,
    val emotionTypeTags: String,
    val physicalSensation: String,
    val physicalSensationTags: String,
    val primaryEmotion: String,
    val clarifyingEmotion: String
)
