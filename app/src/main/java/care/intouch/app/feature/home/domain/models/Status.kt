package care.intouch.app.feature.home.domain.models

import care.intouch.app.R as AppR

enum class Status(val stringId: Int, name: String) {
    TO_DO(stringId = AppR.string.to_do, name = "to do"),
    IN_PROGRESS(stringId = AppR.string.in_progress, name = "in progress"),
    DONE(stringId = AppR.string.done, name = "done")
}