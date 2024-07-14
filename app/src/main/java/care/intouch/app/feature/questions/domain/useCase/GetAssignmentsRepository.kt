package care.intouch.app.feature.questions.domain.useCase

import care.intouch.app.feature.questions.domain.models.Assignments

interface GetAssignmentsRepository {
    suspend fun getAssignments(id: Int): Result<Assignments>
}


