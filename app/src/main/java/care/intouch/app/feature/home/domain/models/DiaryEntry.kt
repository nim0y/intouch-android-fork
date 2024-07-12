package care.intouch.app.feature.home.domain.models

data class DiaryEntry(
    val id: Int,
    val date: String,
    val note: String,
    val moodList: List<Mood>,
    val isSharedWithDoctor: Boolean
)
