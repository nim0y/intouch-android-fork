package care.intouch.app.feature.plan.domain.useCase

import care.intouch.app.feature.plan.domain.models.Assignment
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import care.intouch.uikit.common.ImageVO

interface GetAssignmentsUseCase {
    operator fun invoke(): List<Assignment>
    class Base : GetAssignmentsUseCase {
        override fun invoke(): List<Assignment> {
            return mockAssignments
        }
    }
}

val mockAssignments: List<Assignment> = listOf(
    Assignment(
        id = 1,
        title = "Assignment 1",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "19.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.TO_DO
    ),
    Assignment(
        id = 1,
        title = "Assignment 2",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "20.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.TO_DO
    ),
    Assignment(
        id = 1,
        title = "Assignment 3",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "21.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.IN_PROGRESS
    ),
    Assignment(
        id = 1,
        title = "Assignment 4",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "22.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.IN_PROGRESS
    ),
    Assignment(
        id = 1,
        title = "Assignment 5",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "23.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.DONE
    ),
    Assignment(
        id = 1,
        title = "Assignment 6",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "24.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.DONE
    ),
    Assignment(
        id = 1,
        title = "Assignment 7",
        text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",
        date = "25.06.2024",
        imageUrl = ImageVO.Url(url = "https://www.pakainfo.com/wp-content/uploads/2021/09/image-url-for-testing.jpg"),
        status = AssignmentStatus.DONE
    )
)