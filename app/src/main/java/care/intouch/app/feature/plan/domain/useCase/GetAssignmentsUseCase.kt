package care.intouch.app.feature.plan.domain.useCase

import care.intouch.app.feature.plan.domain.models.Assignment
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import care.intouch.uikit.common.ImageVO
import javax.inject.Inject

interface GetAssignmentsUseCase {
    operator fun invoke(): List<Assignment>
    class Base @Inject constructor() : GetAssignmentsUseCase {
        override fun invoke(): List<Assignment> {
            return mockAssignments
        }
    }
}

val mockAssignments: List<Assignment> = listOf(
    Assignment(
        title = "Assignment 1",
        date = "19.06.2024",
        status = AssignmentStatus.TO_DO
    ),
    Assignment(
        title = "Assignment 2",
        date = "20.06.2024",
        status = AssignmentStatus.TO_DO
    ),
    Assignment(
        title = "Assignment 3",
        date = "21.06.2024",
        status = AssignmentStatus.IN_PROGRESS
    ),
    Assignment(
        title = "Assignment 4",
        date = "22.06.2024",
        status = AssignmentStatus.IN_PROGRESS
    ),
    Assignment(
        title = "Assignment 5",
        date = "23.06.2024",
        status = AssignmentStatus.DONE
    ),
    Assignment(
        title = "Assignment 6",
        date = "24.06.2024",
        status = AssignmentStatus.DONE
    ),
    Assignment(
        title = "Assignment 7",
        date = "25.06.2024",
        status = AssignmentStatus.DONE
    )
)