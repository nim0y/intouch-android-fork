package care.intouch.app.feature.plan.domain.models

import care.intouch.app.R
import care.intouch.uikit.common.StringVO

enum class AssignmentStatus(val value: String) {
    TO_DO(value = "To do"),
    IN_PROGRESS(value = "In progress"),
    DONE(value = "Done"),
    SHOW_ALL(value = "Show all")
}