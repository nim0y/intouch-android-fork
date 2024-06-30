package care.intouch.app.feature.home.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int,
    val status: Status,
    var sharedWithDoc: Boolean,
    val description: String
): Parcelable
