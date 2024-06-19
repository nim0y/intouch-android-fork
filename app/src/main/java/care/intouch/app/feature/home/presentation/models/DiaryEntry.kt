package care.intouch.app.feature.home.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiaryEntry(
    val id: Int,
    val data: String,
    val note: String,
    val moodList: List<Mood>,
    var sharedWithDoc: Boolean
): Parcelable
