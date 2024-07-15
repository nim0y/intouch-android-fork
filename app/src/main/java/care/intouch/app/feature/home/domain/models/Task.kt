package care.intouch.app.feature.home.domain.models

data class Task(
    val id: Int = 0,
    val status: Status = Status.TO_DO,
    val isSharedWithDoctor: Boolean = false,
    val description: String = ""
)
