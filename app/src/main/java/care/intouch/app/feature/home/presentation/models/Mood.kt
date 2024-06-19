package care.intouch.app.feature.home.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mood(val name: String) : Parcelable
